package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

public class PairTest {
    private Pair<String, Integer> pair;

    @Before
    public void setUp(){
        pair = new Pair<>("test", 10);
    }
    
    @Test
    public void testBasic(){
        assertThat(pair.left(), is("test"));
        assertThat(pair.right(), is(10));
    }

    @Test
    public void testMap(){
        pair = pair.map(item -> item + item, value -> value * 2);
        
        assertThat(pair.left(), is("testtest"));
        assertThat(pair.right(), is(20));
    }

    @Test
    public void testReduce(){
        String value = pair.reduce((string, number) -> string + number);

        assertThat(value, is("test10"));
    }
}
