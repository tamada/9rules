package com.github.ninerules.rules;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.NineRulesValidator;
import com.github.ninerules.Target;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.rules.onedot.OneDotPerLineValidator;

public class OneDotPerLineValidatorTest {
    private static final String FILE_PATH = "src/test/resources/hello/src/main/java/sample/hello/GodObject.java";
    private Target target;

    @Before
    public void setUp(){
        Path path = Paths.get(FILE_PATH);
        target = new NineRulesValidator().parse(path);
    }

    @Test
    public void testValidator(){
        Validator validator = new OneDotPerLineValidator();
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(3));
        assertThat(violations.get(0), 
                is(new Violation(OneDotPerLineValidator.ONE_DOT, LineCountsBuilder.build(27))));
        assertThat(violations.get(1), 
                is(new Violation(OneDotPerLineValidator.ONE_DOT, LineCountsBuilder.build(36))));
        assertThat(violations.get(2), 
                is(new Violation(OneDotPerLineValidator.ONE_DOT, LineCountsBuilder.build(48))));
    }

    private List<Violation> getViolations(Map<FileName, List<Violation>> map){
        FileName key = new FileName(FILE_PATH);
        return map.get(key);
    }
}
