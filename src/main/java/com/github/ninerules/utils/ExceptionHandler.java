package com.github.ninerules.utils;

public class ExceptionHandler {
    private ExceptionHandler(){
    }

    public static <T, K, E extends Exception> K performOrThrows(T argumentToFunction,
            K itemWhenThrowsException, ThrowableFunction<T, K, E> function){
        try{ return function.apply(argumentToFunction); }
        catch(Exception e){ }
        return itemWhenThrowsException;
    }

    public static <T, V, K, E extends Exception> K performOrThrows(T argumentToFunction1,
            V argumentToFunction2, K itemWhenThrowsException,
            ThrowableBiFunction<T, V, K, E> function){
        try{ return function.apply(argumentToFunction1, argumentToFunction2); }
        catch(Exception e){ }
        return itemWhenThrowsException;
    }
}
