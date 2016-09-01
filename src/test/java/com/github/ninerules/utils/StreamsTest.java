package com.github.ninerules.utils;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class StreamsTest {
    private Stream<String> streamA;
    private Stream<Integer> streamB;

    @Before
    public void setUp(){
        streamA = Stream.of("a", "b", "c", "d", "e", "f", "g");
        streamB = Stream.iterate(1,  index -> index + 1);
    }

    @Test
    public void testBasic(){
        List<Pair<String, Integer>> list = Streams.zip(streamA, streamB)
                .collect(Collectors.toList());

        assertThat(list.size(), is(7));
        assertThat(list.get(0).left(),  is("a"));
        assertThat(list.get(0).right(), is(1));
        assertThat(list.get(1).left(),  is("b"));
        assertThat(list.get(1).right(), is(2));
        assertThat(list.get(2).left(),  is("c"));
        assertThat(list.get(2).right(), is(3));
        assertThat(list.get(3).left(),  is("d"));
        assertThat(list.get(3).right(), is(4));
        assertThat(list.get(4).left(),  is("e"));
        assertThat(list.get(4).right(), is(5));
        assertThat(list.get(5).left(),  is("f"));
        assertThat(list.get(5).right(), is(6));
        assertThat(list.get(6).left(),  is("g"));
        assertThat(list.get(6).right(), is(7));
    }
}
