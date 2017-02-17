package com.github.ninerules.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import com.github.ninerules.utils.Pair;

public class PredicateHelper<A, R> implements BiFunction<A, A, R>{
    private List<Pair<BiPredicate<A, A>, R>> list = new ArrayList<>();

    public PredicateHelper(){
        // do nothing for default constructor.
    }

    public PredicateHelper(Stream<Pair<BiPredicate<A, A>, R>> pairs){
        pairs.forEach(this::register);
    }

    public void register(Pair<BiPredicate<A, A>, R> pair){
        list.add(pair);
    }

    public void register(BiPredicate<A, A> predicate, R value){
        this.register(Pair.of(predicate, value));
    }

    @Override
    public R apply(A argument1, A argument2) {
        return filter(argument1, argument2)
                .map(Pair::right)
                .findFirst().get();
    }

    private Stream<Pair<BiPredicate<A, A>, R>> filter(A argument1, A argument2){
        return list.stream()
                .filter(pair -> test(pair, argument1, argument2));
    }

    private boolean test(Pair<BiPredicate<A, A>, R> pair, A argument1, A argument2){
        return pair.left()
                .test(argument1, argument2);
    }
}
