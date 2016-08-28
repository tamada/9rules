package com.github.nine.entities;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class LineNumbersTest {
    @Test
    public void testBasic(){
        LineNumbers numbers = new LineNumbersBuilder().of(1, 2, 8, 9, 10).build();
        assertThat(numbers.toString(), is("1,2,8,9,10"));
    }

    @Test
    public void testRange(){
        LineNumbers numbers = new LineNumbersBuilder().range(2, 5).build();
        assertThat(numbers.toString(), is("2,3,4"));
    }
}
