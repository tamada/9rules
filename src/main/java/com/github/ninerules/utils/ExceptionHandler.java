package com.github.ninerules.utils;

public class ExceptionHandler {
    private ExceptionHandler(){
    }

    public static <V, R, E extends Exception> R perform(V argumentToFunction,
            R returnValueIfExceptionThrows, ThrowableFunction<V, R, E> function){
        try{
            return function.apply(argumentToFunction);
        }
        catch(Exception e){
            return returnValueIfExceptionThrows;
        }
    }

    public static <V1, V2, R, E extends Exception> R perform(V1 argument1ToFunction,
            V2 argument2ToFunction, R returnValueIfExceptionThrows,
            ThrowableBiFunction<V1, V2, R, E> function){
        try{
            return function.apply(argument1ToFunction, argument2ToFunction);
        }
        catch(Exception e){
            return returnValueIfExceptionThrows;
        }
    }
}
