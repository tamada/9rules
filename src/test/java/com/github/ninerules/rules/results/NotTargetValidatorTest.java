package com.github.ninerules.rules.results;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.github.ninerules.entities.Context;
import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.NineRulesValidator;
import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.parameters.SourceLength;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.jdtvisitor.JdtValidator;
import com.github.ninerules.rules.onedot.OneDotPerLineValidator;
import com.github.ninerules.rules.smallobject.MethodLengthValidator;
import com.github.ninerules.rules.smallobject.SourceLengthValidator;
import com.github.ninerules.rules.violations.Violation;

public class NotTargetValidatorTest {
    private static final String FILE_PATH = "src/test/resources/hello/src/main/java/sample/hello/GodObjectButNotTarget.java";
    private Target target;

    @Before
    public void setUp(){
        Path path = Paths.get(FILE_PATH);
        target = new NineRulesValidator(new Context(StrictLevel.STRICT, false)).parse(path);
    }

    @Test
    public void testMethodLengthValidator(){
        JdtValidator validator = new MethodLengthValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(0));
    }

    @Test
    public void testOneDotPerLineValidator(){
        Validator validator = new OneDotPerLineValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(0));
    }

    @Test
    public void testSourceLengthValidator(){
        JdtValidator validator = new SourceLengthValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(0));
    }

    @Test
    public void testParameter(){
        JdtValidator validator = new SourceLengthValidator(StrictLevel.STRICT);

        assertThat(validator.parameter(), is(SourceLength.STRICT_LEVEL));
    }

    private List<Violation> getViolations(Map<FileName, List<Violation>> map){
        FileName key = new FileName(FILE_PATH);
        return map.get(key);
    }
}
