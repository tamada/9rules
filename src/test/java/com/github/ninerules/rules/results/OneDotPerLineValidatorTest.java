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
import com.github.ninerules.rules.onedot.OneDotPerLineValidator;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;

public class OneDotPerLineValidatorTest {
    private static final String FILE_PATH = "src/test/resources/hello/src/main/java/sample/hello/GodObject.java";
    private Target target;

    @Before
    public void setUp(){
        Path path = Paths.get(FILE_PATH);
        target = new NineRulesValidator(StrictLevel.STRICT).parse(path);
    }

    @Test
    public void testValidator(){
        Validator validator = new OneDotPerLineValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        ViolationType type = new ViolationType(OneDotPerLineValidator.ONE_DOT, validator.parameter());
        assertThat(violations.size(), is(2));
        assertThat(violations.get(0), 
                is(new Violation(type, LineCountsBuilder.build(builder -> builder.of(28)))));
        assertThat(violations.get(1), 
                is(new Violation(type, LineCountsBuilder.build(builder -> builder.of(29)))));
    }

    @Test
    public void testParameter(){
        Validator validator = new OneDotPerLineValidator(StrictLevel.STRICT);

        assertThat(validator.parameter(), is(DotCount.STRICT_LEVEL));
    }

    private List<Violation> getViolations(Map<FileName, List<Violation>> map){
        FileName key = new FileName(FILE_PATH);
        return map.get(key);
    }
}
