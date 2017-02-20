package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

public class ArgumentsTest {
    @Test
    public void testBasic(){
        Arguments args = new Arguments(Stream.of("arg1", "arg2", "arg3").map(string -> new Argument(string)));

        Argument[] array = args.stream().toArray(size -> new Argument[size]);
        assertThat(array.length, is(3));

        assertThat(array[0], is(new Argument("arg1")));
        assertThat(array[1], is(new Argument("arg2")));
        assertThat(array[2], is(new Argument("arg3")));
    }
}
