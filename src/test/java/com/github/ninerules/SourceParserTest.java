package com.github.ninerules;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.nio.file.Paths;

import org.junit.Test;

public class SourceParserTest {
    @Test
    public void testReadSource() throws Exception{
        SourceParser parser = new SourceParser(Paths.get("src/test/resources/tinysource"));
        assertThat(parser.parse(), is("aaa\nbbb"));
    }

    @Test
    public void testNonExistFile() throws Exception{
        SourceParser parser = new SourceParser(Paths.get("non/exists/file"));
        assertThat(parser.parse(), is(""));
    }
}
