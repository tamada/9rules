package com.github.ninerules.utils;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class PrivateConstructorTester {
    public <T> void testConstructor(Class<T> targetClass) 
            throws NoSuchMethodException, InstantiationException,
                   IllegalAccessException, InvocationTargetException{
        Constructor<T> constructor = findAccesibleConstructor(targetClass);
        Object targetObject = constructor.newInstance();

        assertThat(targetObject, is(not(nullValue())));
        assertThat(targetObject, is(instanceOf(targetClass)));
    }

    private <T> Constructor<T> findAccesibleConstructor(Class<T> targetClass) throws NoSuchMethodException {
        Constructor<T> constructor = targetClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        return constructor;
    }
}
