package com.github.ninerules.cli;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.github.ninerules.StrictLevel;

public class OptionTest {
    @Test
    public void testBasic(){
        assertThat(Option.STRICT_OPTION,  is(new Option("--strict")));
        assertThat(Option.GENERAL_OPTION, is(new Option("--general")));
        assertThat(Option.ROUGH_OPTION,   is(new Option("--rough")));

        assertThat(Option.STRICT_OPTION.toLevel(),  is(StrictLevel.STRICT));
        assertThat(Option.GENERAL_OPTION.toLevel(), is(StrictLevel.GENERAL));
        assertThat(Option.ROUGH_OPTION.toLevel(),   is(StrictLevel.ROUGH));

        assertThat(new Option("--help").toLevel(),  is(StrictLevel.STRICT));
    }

    @Test
    public void testEquals(){
        assertThat(Option.STRICT_OPTION, is(new Option("--strict")));
        assertThat(Option.STRICT_OPTION, is(not(new Option("--rough"))));
        assertThat(Option.STRICT_OPTION, is(not(new Object())));
    }
}
