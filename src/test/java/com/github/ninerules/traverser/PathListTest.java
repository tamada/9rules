package com.github.ninerules.traverser;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;

public class PathListTest {
    private com.github.ninerules.traverser.Paths pathlist;

    @Before
    public void setUp(){
        pathlist = new com.github.ninerules.traverser.Paths(Arrays.asList(
            Paths.get("src/test/resources/hello/pom.xml"),
            Paths.get("src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java"),
            Paths.get("src/test/resources/hello/src/main/java/sample/hello/Launcher.java")
        ));
    }

    @Test
    public void testBasic(){
        List<Path> list = pathlist.stream()
                .sorted()
                .collect(Collectors.toList());

        assertThat(list.size(), is(3));
        assertThat(list.get(0), is(Paths.get("src/test/resources/hello/pom.xml")));
        assertThat(list.get(1), is(Paths.get("src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java")));
        assertThat(list.get(2), is(Paths.get("src/test/resources/hello/src/main/java/sample/hello/Launcher.java")));
    }
}
