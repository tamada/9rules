package com.github.ninerules.entities;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Objects;
import java.util.stream.Stream;

import org.junit.Test;

public class LineCountsTest {
    @Test
    public void testBasic(){
        LineCounts numbers = LineCountsBuilder.build(builder -> builder.of(1, 2, 8, 9, 10));
        assertThat(numbers.toString(), is("1,2,8,9,10"));

        LineCounts numbers2 = LineCountsBuilder.build(builder -> builder.of(1, 2, 8, 9, 10));
        assertThat(numbers, is(numbers2));
    }

    @Test
    public void testRange(){
        LineCounts numbers = LineCountsBuilder.build(builder -> builder.range(2, 5));
        assertThat(numbers.toString(), is("2,3,4"));
    }

    @Test
    public void testBuildFromStream(){
        Stream<LineCount> stream = Stream.of(1, 2, 3, 8, 9).map(line -> new LineCount(line)); 
        LineCounts numbers = LineCountsBuilder.build(builder -> builder.stream(stream));
        assertThat(numbers.toString(), is("1,2,3,8,9"));
    }

    @Test
    public void testBuildFromArray(){
        LineCounts numbers = new LineCounts(new LineCount(1), new LineCount(4));
        assertThat(numbers.toString(), is("1,4"));

        LineCounts numbers2 = LineCountsBuilder.build(builder -> builder.of(1, 4));
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
