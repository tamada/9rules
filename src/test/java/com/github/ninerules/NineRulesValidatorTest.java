package com.github.ninerules;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import com.github.ninerules.entities.Context;
import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.parameters.NullParameter;
import com.github.ninerules.rules.accessor.NoAccessorValidator;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;
import com.github.ninerules.traverser.Traverser;

public class NineRulesValidatorTest {
    private Path path = Paths.get("src/test/resources/hello/src/main/java");
    private List<Path> list;

    @Before
    public void setUp(){
        Traverser traverser = new Traverser((name, attributes) -> name.toString().endsWith(".java"));
        list = traverser.stream(path)
                .collect(Collectors.toList());
    }

    @Test
    public void testBasic(){
        NineRulesValidator validator = new NineRulesValidator(new Context(StrictLevel.STRICT, false));
        Results results = validator.validate(list);

        assertThat(results.contains(
            new FileName(path.resolve("sample/hello/HelloWorld.java")),
            new Violation(new ViolationType(NoAccessorValidator.SETTER, NullParameter.parameter()),
                    LineCountsBuilder.builder().of(10).build())
        ), is(true));
        assertThat(results.contains(
            new FileName(path.resolve("sample/hello/HelloWorld.java")),
            new Violation(new ViolationType(NoAccessorValidator.GETTER, NullParameter.parameter()),
                    LineCountsBuilder.builder().of(14).build())
        ), is(true));
    }
}
