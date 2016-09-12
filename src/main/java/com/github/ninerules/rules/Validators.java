package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.ResultsAppender;

public class Validators{
    private List<Validator> list = new ArrayList<>();

    Validators(){
    }
    
    void register(Validator validator){
        list.add(validator);
    }

    private Stream<Validator> stream(){
        return list.stream();
    }

    public Results validateEach(StrictLevel level, Target unit){
        return validate(level, unit)
                .orElse(Results.empty());
    }
    
    public Optional<Results> validate(StrictLevel level, Target unit){
        return stream()
                .map(checker -> unit.accept(checker))
                .reduce((r1, r2) -> new ResultsAppender(r1).append(r2));
    }
}
