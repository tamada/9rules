package com.github.ninerules;

@FunctionalInterface
public interface ThrowableConsumer{
    void consume() throws Exception;
}
