package com.github.ninerules.traverser;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class FileVisitorTest {
    private Path basePath = Paths.get("target/test-classes/hello/src/main");

    @Test
    public void testBasic() throws IOException{
        FileVisitor visitor = new FileVisitor(null);
        Files.walkFileTree(basePath, visitor);

        com.github.ninerules.traverser.Paths paths = visitor.createPaths();
        List<Path> list = paths.stream()
                .map(path -> basePath.relativize(path))
                .sorted().collect(Collectors.toList());

        assertThat(list.size(), is(5));
        assertThat(list.get(0), is(Paths.get("java/sample/hello/GodObject.java")));
        assertThat(list.get(1), is(Paths.get("java/sample/hello/HelloWorld.java")));
        assertThat(list.get(2), is(Paths.get("java/sample/hello/Launcher.java")));
        assertThat(list.get(3), is(Paths.get("webapp/WEB-INF/web.xml")));
        assertThat(list.get(4), is(Paths.get("webapp/index.html")));
    }
}
