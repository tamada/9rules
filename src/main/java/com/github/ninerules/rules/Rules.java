package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.Target;
import com.github.ninerules.fcc.FCCVisitor;
import com.github.ninerules.rules.noaccessor.NoAccessorVisitor;

public class Rules {
    private List<ViolationChecker> list = new ArrayList<>();

    public Rules(){
        list.add(new NoAccessorVisitor());
        list.add(new FCCVisitor());
    }

    public Results check(final Target unit){
        return list.stream()
                .map(checker -> accept(unit, checker))
                .reduce((r1, r2) -> r1.append(r2))
                .orElse(Results.empty());
    }

    private Results accept(Target target, ViolationChecker checker){
        return target.accept(checker);
    }
}
