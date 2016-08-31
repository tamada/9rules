package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;

import com.github.ninerules.Target;
import com.github.ninerules.rules.accessor.NoAccessorValidator;
import com.github.ninerules.rules.elsestatement.NoElseStatementValidator;
import com.github.ninerules.rules.fieldcount.FieldCountValidator;
import com.github.ninerules.rules.firstclasscollection.FirstClassCollectionValidator;
import com.github.ninerules.rules.indentlevel.IndentLevelValidator;
import com.github.ninerules.rules.primitive.NoPrimitivesValidator;
import com.github.ninerules.rules.smallobject.MethodLengthValidator;
import com.github.ninerules.rules.smallobject.SourceLengthValidator;

public class Rules {
    private List<Validator> list = new ArrayList<>();

    public Rules(){
        list.add(new IndentLevelValidator());
        list.add(new NoElseStatementValidator());
        list.add(new NoPrimitivesValidator());
        list.add(new MethodLengthValidator());
        list.add(new SourceLengthValidator());
        list.add(new FieldCountValidator());
        list.add(new FirstClassCollectionValidator());
        list.add(new NoAccessorValidator());
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
