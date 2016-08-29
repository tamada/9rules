package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class LineCountsTest {
    @Test
    public void testBasic(){
        LineCounts numbers = LineCountsBuilder.create().of(1, 2, 8, 9, 10).build();
        assertThat(numbers.toString(), is("1,2,8,9,10"));
    }

    @Test
    public void testRange(){
        LineCounts numbers = LineCountsBuilder.create().range(2, 5).build();
        assertThat(numbers.toString(), is("2,3,4"));
    }

    @Test
    public void testBuildFromStream(){
        LineCounts numbers = LineCountsBuilder
                .create()
                .build(Stream.of(1, 2, 3, 8, 9)
                        .map(line -> new LineCount(line)));
        assertThat(numbers.toString(), is("1,2,3,8,9"));
    }

    @Test
    public void testBuildFromArray(){
        LineCounts numbers = new LineCounts(new LineCount(1), new LineCount(4));
        assertThat(numbers.toString(), is("1,4"));
    }
}
