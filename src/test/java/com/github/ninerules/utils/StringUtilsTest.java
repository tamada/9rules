package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;;

public class StringUtilsTest {
    @Test
    public void testEmptyString(){
        assertThat(StringUtils.isEmptyString(""), is(true));
        assertThat(StringUtils.isEmptyString("abc"), is(not(true)));
    }

    @Test
    public void testIfNotEmpty(){
        assertThat(StringUtils.ifNotEmpty("", (value) -> value + value), is(Optional.empty()));
        assertThat(StringUtils.ifNotEmpty("abc", (value) -> value + value), is(Optional.of("abcabc")));
    }
}
