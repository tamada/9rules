package com.github.ninerules.utils;

import java.util.Optional;

public class ExceptionHandler {
    private ExceptionHandler(){
    }

    public static <V, R, E extends Exception> Optional<R> perform(V argumentToFunction, 
            ThrowableFunction<V, R, E> function){
        try{
            return Optional.of(performFunction(function, argumentToFunction));
        } catch(Exception e){
            return Optional.empty();
        }
    }

    public static <V1, V2, R, E extends Exception> Optional<R> perform(V1 argumentToFunction1,
            V2 argumentToFunction2, ThrowableBiFunction<V1, V2, R, E> function){
        try{
            return Optional.of(performFunction(function, argumentToFunction1, argumentToFunction2));
        } catch(Exception e){
            return Optional.empty();
        }
    }

    private static <V, R, E extends Exception> R performFunction(
            ThrowableFunction<V, R, E> function, V argumentToFunction) throws E{
        return function.apply(argumentToFunction);
    }

    private static <V1, V2, R, E extends Exception> R performFunction(
            ThrowableBiFunction<V1, V2, R, E> function, V1 argumentToFunction1,
            V2 argumentToFunction2) throws E{
        return function.apply(argumentToFunction1, argumentToFunction2);
    }
}
