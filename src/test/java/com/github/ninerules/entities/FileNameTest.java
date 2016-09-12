package com.github.ninerules.entities;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
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
        assertThat(name, is(not(new FileName("DifferentName.java"))));
    }

    @Test
    public void testCompareToo(){
        FileName name1 = new FileName(Paths.get("FileNameTest1.java"));
        FileName name2 = new FileName(Paths.get("FileNameTest2.java"));

        assertThat(name1, is(lessThan(name2)));
        assertThat(name2, is(greaterThan(name1)));
    }
}
