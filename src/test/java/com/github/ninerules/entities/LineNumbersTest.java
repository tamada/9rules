package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class LineNumbersTest {
    @Test
    public void testBasic(){
        LineNumbers numbers = LineNumbersBuilder.create().of(1, 2, 8, 9, 10).build();
        assertThat(numbers.toString(), is("1,2,8,9,10"));
    }

    @Test
    public void testRange(){
        LineNumbers numbers = LineNumbersBuilder.create().range(2, 5).build();
        assertThat(numbers.toString(), is("2,3,4"));
    }

    @Test
    public void testBuildFromStream(){
        LineNumbers numbers = LineNumbersBuilder
                .create()
                .build(Stream.of(1, 2, 3, 8, 9)
                        .map(line -> new LineNumber(line)));
        assertThat(numbers.toString(), is("1,2,3,8,9"));
    }

    @Test
    public void testBuildFromArray(){
        LineNumbers numbers = new LineNumbers(new LineNumber(1), new LineNumber(4));
        assertThat(numbers.toString(), is("1,4"));
    }
}
