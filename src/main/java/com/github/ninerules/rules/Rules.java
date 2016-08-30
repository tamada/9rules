package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.Target;
import com.github.ninerules.fcc.FirstClassCollectionValidator;
import com.github.ninerules.rules.noaccessor.NoAccessorValidator;
import com.github.ninerules.so.MethodLengthValidator;
import com.github.ninerules.so.SourceLengthValidator;

public class Rules {
    private List<Validator> list = new ArrayList<>();

    public Rules(){
        list.add(new NoAccessorValidator());
        list.add(new FirstClassCollectionValidator());
        list.add(new MethodLengthValidator());
        list.add(new SourceLengthValidator());
    }

    public Results validate(final Target unit){
        return list.stream()
                .map(checker -> accept(unit, checker))
                .reduce((r1, r2) -> r1.append(r2))
                .orElse(Results.empty());
    }

    private Results accept(Target target, Validator checker){
        return target.accept(checker);
    }
}
