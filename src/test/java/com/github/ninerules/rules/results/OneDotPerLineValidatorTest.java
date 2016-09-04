package com.github.ninerules.rules.results;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.NineRulesValidator;
import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.parameters.DotCount;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
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
        Validator<DotCount> validator = new OneDotPerLineValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(2));
        assertThat(violations.get(0), 
                is(new Violation(OneDotPerLineValidator.ONE_DOT, LineCountsBuilder.build(28))));
        assertThat(violations.get(1), 
                is(new Violation(OneDotPerLineValidator.ONE_DOT, LineCountsBuilder.build(29))));
    }

    private List<Violation> getViolations(Map<FileName, List<Violation>> map){
        FileName key = new FileName(FILE_PATH);
        return map.get(key);
    }
}
