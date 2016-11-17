package com.github.ninerules.traverser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class TraverserTest {
    private Path base = Paths.get("target/test-classes/hello/src");

    @Test
    public void testBasic(){
        Traverser traverser = new Traverser();
        List<Path> list = traverser.stream(base)
                .sorted()
                .map(path -> base.relativize(path))
                .collect(Collectors.toList());

        assertThat(list.size(), is(6));
        assertThat(list.get(0), is(Paths.get("main/java/sample/hello/GodObject.java")));
        assertThat(list.get(1), is(Paths.get("main/java/sample/hello/GodObjectButNotTarget.java")));
        assertThat(list.get(2), is(Paths.get("main/java/sample/hello/HelloWorld.java")));
        assertThat(list.get(3), is(Paths.get("main/java/sample/hello/Launcher.java")));
        assertThat(list.get(4), is(Paths.get("main/webapp/WEB-INF/web.xml")));
        assertThat(list.get(5), is(Paths.get("main/webapp/index.html")));
    }

    @Test
    public void testFilter(){
        Traverser traverser = new Traverser((name, attributes) -> name.toString().endsWith(".java"));
        List<Path> list = traverser.stream(base)
                .sorted()
                .map(path -> base.relativize(path))
                .collect(Collectors.toList());

        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(Paths.get("main/java/sample/hello/GodObject.java")));
        assertThat(list.get(1), is(Paths.get("main/java/sample/hello/GodObjectButNotTarget.java")));
        assertThat(list.get(2), is(Paths.get("main/java/sample/hello/HelloWorld.java")));
        assertThat(list.get(3), is(Paths.get("main/java/sample/hello/Launcher.java")));
    }

    @Test
    public void testNonExistsDirectory(){
        Traverser traverser = new Traverser();
        List<Path> list = traverser.stream(Paths.get("src/main/nodirectory"))
                .sorted()
                .map(path -> base.relativize(path))
                .collect(Collectors.toList());
        assertThat(list.size(), is(0));
    }

    @Test
    public void testExtensionFilter(){
        Traverser traverser = new Traverser(new ExtensionFilter(".java"));
        List<Path> list = traverser.stream(base)
                .sorted()
                .map(path -> base.relativize(path))
                .collect(Collectors.toList());

        assertThat(list.size(), is(4));
        assertThat(list.get(0), is(Paths.get("main/java/sample/hello/GodObject.java")));
        assertThat(list.get(1), is(Paths.get("main/java/sample/hello/GodObjectButNotTarget.java")));
        assertThat(list.get(2), is(Paths.get("main/java/sample/hello/HelloWorld.java")));
        assertThat(list.get(3), is(Paths.get("main/java/sample/hello/Launcher.java")));
    }
}
