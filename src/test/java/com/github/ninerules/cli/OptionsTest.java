package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.Stream;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class OptionsTest {
    @Test
    public void testBasic(){
        Options options = new Options(Stream.of("--help", "--rough", "--strict").map(string -> new Option(string)));

        Option[] array = options.options().toArray(size -> new Option[size]);
        assertThat(array.length, is(3));

        assertThat(array[0], is(new Option("--help")));
        assertThat(array[1], is(new Option("--rough")));
        assertThat(array[2], is(new Option("--strict")));

        assertThat(options.level(), is(StrictLevel.ROUGH));

        assertThat(options.isSpecified(Option.HELP_OPTION), is(true));
        assertThat(options.isSpecified(Option.STRICT_OPTION), is(true));
        assertThat(options.isSpecified(Option.GENERAL_OPTION), is(false));
        assertThat(options.isSpecified(Option.ROUGH_OPTION), is(true));
    }
}
