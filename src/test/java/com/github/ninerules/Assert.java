package com.github.ninerules;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.InvocationTargetException;

import com.github.ninerules.utils.PrivateConstructorTester;
import com.github.ninerules.utils.ThrowableConsumer;

public class Assert {
    public static <T extends Exception> void assertThrows(Class<T> exceptionClass,
            ThrowableConsumer<Exception> consumer){
        boolean thrown = false;
        try {
            consumer.consume();
        } catch (Exception ex) {
            assertTrue(exceptionClass.isInstance(ex));
            thrown = true;
        }
        assertTrue(thrown);
    }

    public static <T> void assertAvailablePrivateConstructor(Class<T> targetClass)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException,
            InvocationTargetException{
        PrivateConstructorTester tester = new PrivateConstructorTester();
        tester.testConstructor(targetClass);
    }
}
