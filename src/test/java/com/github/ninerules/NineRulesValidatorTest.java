package com.github.ninerules;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.rules.Results;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.accessor.NoAccessorValidator;
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
        NineRulesValidator validator = new NineRulesValidator();
        Results results = validator.validate(list);

        assertThat(results.contains(
            new FileName(path.resolve("sample/hello/HelloWorld.java")),
            new Violation(NoAccessorValidator.SETTER, LineCountsBuilder.build(10))
        ), is(true));
        assertThat(results.contains(
            new FileName(path.resolve("sample/hello/HelloWorld.java")),
            new Violation(NoAccessorValidator.GETTER, LineCountsBuilder.build(14))
        ), is(true));
    }
}
