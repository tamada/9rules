package com.github.nine.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.nine.Target;
import com.github.nine.fcc.FCCVisitor;
import com.github.nine.rules.noaccessor.NoAccessorVisitor;

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
