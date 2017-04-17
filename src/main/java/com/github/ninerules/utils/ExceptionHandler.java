package com.github.ninerules.utils;

import java.util.Optional;

public class ExceptionHandler {
    private ExceptionHandler(){
    }

    public static <V, R, E extends Exception> Optional<R> perform(V argumentToFunction, 
            ThrowableFunction<V, R, E> function){
        try{
            return optionalOf(function.apply(argumentToFunction));
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public static <V1, V2, R, E extends Exception> Optional<R> perform(V1 argumentToFunction1,
            V2 argumentToFunction2, ThrowableBiFunction<V1, V2, R, E> function){
        try{
            return optionalOf(function.apply(argumentToFunction1, argumentToFunction2));
        } catch(Exception e){
            return Optional.empty();
        }
    }

    private static <R> Optional<R> optionalOf(R returnValue){
        return Optional.of(returnValue);
    }
}
