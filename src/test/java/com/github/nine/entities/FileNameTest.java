package com.github.nine.entities;

import static org.junit.Assert.assertThat;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.is;

import org.junit.Test;

public class FileNameTest {
    @Test
    public void testStringConstructor(){
        FileName name = new FileName("FileNameTest.java");

        assertThat(name.toString(), is("FileNameTest.java"));
    }

    @Test
    public void testPathConstructor(){
        FileName name = new FileName(Paths.get("FileNameTest.java"));

        assertThat(name.toString(), is("FileNameTest.java"));
    }
}
