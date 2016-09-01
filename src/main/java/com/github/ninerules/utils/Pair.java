package com.github.ninerules.utils;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * 
 * @see http://d.hatena.ne.jp/nowokay/20140321
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

    public<M, N> Pair<M, N> map(Function<T, M> lfunc, Function<U, N> rfunc){
        return of(lfunc.apply(left),
                rfunc.apply(right));
    }
}
