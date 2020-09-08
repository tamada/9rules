package com.github.ninerules.utils;

import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Either<E extends Throwable, V> {
    private Optional<E> exception;
    private Optional<V> value;

    private Either(Optional<E> exception, Optional<V> value){
        this.exception = exception;
        this.value = value;
    }

    public static <E extends Throwable, V> Either<E, V> ofException(E exception) {
        return new Either<>(Optional.of(exception), Optional.empty());
    }

    public static <E extends Throwable, V> Either<E, V> ofValue(V value) {
        return new Either<>(Optional.empty(), Optional.of(value));
    }

    public boolean isException() {
        return exception.isPresent();
    }

    public Optional<E> exception() {
        return exception;
    }

    public Optional<V> value() {
        return value;
    }
    /**
     * If {@link #isException <code>isException</code>} returns true,
     * this method returns empty stream,
     * otherwise, returned stream contains only one element of {@link #value <code>value</code>}.
     */
    public Stream<V> stream() {
        return value().map(Stream::of)
                .orElseGet(() -> Stream.empty());
    }

    public Optional<Either<E, V>> filter(Predicate<V> predicate){
        if(isException() || !predicate.test(value.get()))
            return Optional.empty();
        return Optional.of(this);
    }

    public <R> R map(BiFunction<E, V, R> function){
        return function.apply(exception.orElse(null),
                value.orElse(null));
    }

    public <M> Either<E, M> map(Function<V, M> function){
        return new Either<>(exception, value.map(function));
    }

    public <E2 extends Exception, M> Either<E2, M> map(Function<E, E2> function1, Function<V, M> function2){
        return new Either<>(exception.map(function1), value.map(function2));
    }
}
