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
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.parameters.IndentLevel;
import com.github.ninerules.rules.indentlevel.IndentLevelValidator;
import com.github.ninerules.rules.jdtvisitor.JdtValidator;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;

public class IndentLevelValidatorTest {
    private static final String FILE_PATH = "src/test/resources/hello/src/main/java/sample/hello/GodObject.java";
    private Target target;

    @Before
    public void setUp(){
        Path path = Paths.get(FILE_PATH);
        target = new NineRulesValidator(new Context(StrictLevel.STRICT, false)).parse(path);
    }

    @Test
    public void testValidator(){
        JdtValidator validator = new IndentLevelValidator(StrictLevel.STRICT);
        Results results = target.accept(validator);
        List<Violation> violations = getViolations(results.violations);

        assertThat(violations.size(), is(1));
        assertThat(violations.get(0), 
                is(new Violation(
                        new ViolationType(IndentLevelValidator.INDENT_LEVEL, validator.parameter()),
                        LineCountsBuilder.builder().of(43).build())));
    }

    @Test
    public void testParameter(){
        JdtValidator validator = new IndentLevelValidator(StrictLevel.STRICT);

        assertThat(validator.parameter(), is(IndentLevel.STRICT_LEVEL));
    }

    private List<Violation> getViolations(Map<FileName, List<Violation>> map){
        FileName key = new FileName(FILE_PATH);
        return map.get(key);
    }
}
