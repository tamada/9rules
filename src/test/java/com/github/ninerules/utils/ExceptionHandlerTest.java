package com.github.ninerules.utils;

import static com.github.ninerules.Assert.assertAvailablePrivateConstructor;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

public class ExceptionHandlerTest {
    @Test
    public void testHandler(){
        ThrowableFunction<String, Integer, NullPointerException> function = (string) -> string.length();
        
        assertThat(ExceptionHandler.perform("abc", function).orElse(0), is(3));
        assertThat(ExceptionHandler.perform("abc", function), is(Optional.of(3)));
        assertThat(ExceptionHandler.perform(null,  function), is(Optional.empty()));
    }

    @Test
    public void testBiHandler(){
        ThrowableBiFunction<String, String, Integer, NullPointerException> function = 
                (string1, string2) -> string1.length() + string2.length();
        
        assertThat(ExceptionHandler.perform("abc", "hoge", function), is(Optional.of(7)));
        assertThat(ExceptionHandler.perform(null, "hoge",  function), is(Optional.empty()));
        assertThat(ExceptionHandler.perform("hoge", null, function), is(Optional.empty()));
        assertThat(ExceptionHandler.perform(null, null, function), is(Optional.empty()));
    }

    @Test
    public void testDefaultPrivateConstructor() throws Exception{
        assertAvailablePrivateConstructor(ExceptionHandler.class);
    }    
}
