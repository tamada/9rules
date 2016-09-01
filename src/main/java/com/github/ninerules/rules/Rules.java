package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.github.ninerules.Target;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.ResultsAppender;

public class Rules {
    private List<Validator> list = new ArrayList<>();

    public Rules(){
        ServiceLoader<Validator> serviceLoader = createLoader(Validator.class);
        serviceLoader
        .forEach(item -> list.add(item));
    }

    private ServiceLoader<Validator> createLoader(Class<Validator> clazz){
        return ServiceLoader.load(clazz);
    }

    public Results validate(final Target unit){
        return list.stream()
                .map(checker -> accept(unit, checker))
                .reduce((r1, r2) -> new ResultsAppender(r1).append(r2))
                .orElse(Results.empty());
    }

    private Results accept(Target target, Validator checker){
        return target.accept(checker);
    }
}
