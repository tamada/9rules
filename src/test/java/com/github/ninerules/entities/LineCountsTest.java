package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Objects;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class LineCountsTest {
    @Test
    public void testBasic(){
        LineCounts numbers = new LineCountsBuilder()
                .of(1, 2, 8, 9, 10).build();
        assertThat(numbers.toString(), is("1,2,8,9,10"));

        LineCounts numbers2 = new LineCountsBuilder()
                .of(1, 2, 8, 9, 10).build();
        assertThat(numbers, is(numbers2));
    }

    @Test
    public void testRange(){
        LineCounts numbers = new LineCountsBuilder()
                .of(2, 3, 4).build();
        assertThat(numbers.toString(), is("2,3,4"));
    }

    @Test
    public void testBuildFromStream(){
        Stream<LineCount> stream = IntStream.of(1, 2, 3, 8, 9).mapToObj(line -> new LineCount(line)); 
        LineCounts numbers = new LineCountsBuilder()
                .stream(stream).build();
        assertThat(numbers.toString(), is("1,2,3,8,9"));
    }

    @Test
    public void testBuildFromArray(){
        LineCounts numbers = new LineCounts(new LineCount(1), new LineCount(4));
        assertThat(numbers.toString(), is("1,4"));

        LineCounts numbers2 = new LineCountsBuilder().of(1, 4).build();
        assertThat(numbers, is(numbers2));
    }

    @Test
    public void testNotEquals(){
        LineCounts numbers = new LineCounts(new LineCount(1), new LineCount(4));

        assertThat(numbers, is(not(new Object())));
    }

    @Test
    public void testHash(){
        LineCount[] counts = new LineCount[] { new LineCount(1), new LineCount(4) };

        assertThat(new LineCounts(counts).hashCode(), is(Objects.hash(new LineCount(1), new LineCount(4))));
    }
}
