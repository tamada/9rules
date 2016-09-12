package com.github.ninerules.utils;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ExceptionHandlerTest {
    @Test
    public void testHandler(){
        ThrowableFunction<String, Integer, NullPointerException> function = (string) -> string.length();
        
        assertThat(ExceptionHandler.performOrThrows("abc", 0, function), is(3));
        assertThat(ExceptionHandler.performOrThrows(null, 0, function), is(0));
    }

    @Test
    public void testBiHandler(){
        ThrowableBiFunction<String, String, Integer, NullPointerException> function = 
                (string1, string2) -> string1.length() + string2.length();
        
        assertThat(ExceptionHandler.performOrThrows("abc", "hoge", 0, function), is(7));
        assertThat(ExceptionHandler.performOrThrows(null, "hoge", -1, function), is(-1));
        assertThat(ExceptionHandler.performOrThrows("hoge", null, -1, function), is(-1));
        assertThat(ExceptionHandler.performOrThrows(null, null, -1, function), is(-1));
    }
}
