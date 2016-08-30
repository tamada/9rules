package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.nio.file.Paths;

import org.junit.Test;

public class FileNameTest {
    @Test
    public void testStringConstructor(){
        FileName name = new FileName("FileNameTest.java");

        assertThat(name.toString(), is("FileNameTest.java"));
        assertThat(name, is(new FileName("FileNameTest.java")));
    }

    @Test
    public void testPathConstructor(){
        FileName name = new FileName(Paths.get("FileNameTest.java"));

        assertThat(name.toString(), is("FileNameTest.java"));
        assertThat(name, is(new FileName("FileNameTest.java")));
    }

    @Test
    public void testNotEquals(){
        FileName name = new FileName(Paths.get("FileNameTest.java"));

        assertThat(name, is(not(new Object())));
    }
}
