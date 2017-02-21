package com.github.ninerules.utils;

import static com.github.ninerules.Assert.assertAvailablePrivateConstructor;

import org.junit.Test;

public class LoggingHelperTest {
    @Test
    public void testDefaultPrivateConstructor() throws Exception{
        assertAvailablePrivateConstructor(LoggingHelper.class);
    }    
}
