package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExceptionHandlerTest {
    @Test
    public void testHandler(){
        ThrowableFunction<String, Integer, NullPointerException> function = (string) -> string.length();
        
        assertThat(ExceptionHandler.perform("abc", 0, function), is(3));
        assertThat(ExceptionHandler.perform(null, 0, function), is(0));
    }

    @Test
    public void testBiHandler(){
        ThrowableBiFunction<String, String, Integer, NullPointerException> function = 
                (string1, string2) -> string1.length() + string2.length();
        
        assertThat(ExceptionHandler.perform("abc", "hoge", 0, function), is(7));
        assertThat(ExceptionHandler.perform(null, "hoge", -1, function), is(-1));
        assertThat(ExceptionHandler.perform("hoge", null, -1, function), is(-1));
        assertThat(ExceptionHandler.perform(null, null, -1, function), is(-1));
    }
}
