package com.github.nine;

@FunctionalInterface
public interface ThrowableConsumer{
    void consume() throws Exception;
}
