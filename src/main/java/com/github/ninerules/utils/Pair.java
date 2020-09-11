package com.github.ninerules.utils;

import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * The class holds two objects as a pair.
 * 
 * @see <a href="http://d.hatena.ne.jp/nowokay/20140321">http://d.hatena.ne.jp/nowokay/20140321</a>
 */
public class Pair<T, U> {
    T left;
    U right;

    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    public T left(){
        return left;
    }

    public U right(){
        return right;
    }

    public static <M, N> Pair<M, N> of(M left, N right){
        return new Pair<>(left, right);
    }

    public<R> R reduce(BiFunction<T, U, R> func){
        return func.apply(left, right);
    }

    public void apply(BiConsumer<T, U> action){
        action.accept(left, right);
    }

    public<M, N> Pair<M, N> map(Function<T, M> lfunc, Function<U, N> rfunc){
        return of(lfunc.apply(left),
                rfunc.apply(right));
    }
}
