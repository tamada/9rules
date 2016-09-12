package com.github.ninerules;

import static org.junit.Assert.assertTrue;

import com.github.ninerules.utils.ThrowableConsumer;

public class Assert {
    public static void assertThrows(Class<? extends Exception> exceptionClass, ThrowableConsumer<Exception> consumer){
        boolean thrown = false;
        try {
            consumer.consume();
        } catch (Exception ex) {
            assertTrue(exceptionClass.isInstance(ex));
            thrown = true;
        }
        assertTrue(thrown);
    }
}
