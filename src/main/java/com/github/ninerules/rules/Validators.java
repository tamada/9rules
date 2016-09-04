package com.github.ninerules.rules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.results.ResultsAppender;
import com.github.ninerules.utils.ServiceLoader;

@SuppressWarnings("rawtypes")
public class Validators{
    private List<Validator<?>> list = new ArrayList<>();

    public Validators(StrictLevel level){
        ServiceLoader<Validator> serviceLoader = createLoader(Validator.class);
        serviceLoader.stream()
        .map(clazz -> instantiate(clazz, level))
        .forEach(item -> register(level, item));
    }

    private void register(StrictLevel level, Validator<?> validator){
        list.add(validator);
    }

    private Validator<?> instantiate(Class<Validator> clazz, StrictLevel level){
        try {
            Constructor<Validator> constructor = clazz.getConstructor(StrictLevel.class);
            return constructor.newInstance(level);
        } catch (NoSuchMethodException | SecurityException | InstantiationException 
                | IllegalAccessException | IllegalArgumentException 
                | InvocationTargetException e) {
        }
        return null;
    }

    private ServiceLoader<Validator> createLoader(Class<Validator> clazz){
        return ServiceLoader.load(clazz);
    }

    private Stream<Validator<?>> stream(){
        return list.stream();
    }
    
    public Results validateEach(StrictLevel level, Target unit){
        return stream().map(checker -> unit.accept(checker))
                .reduce((r1, r2) -> new ResultsAppender(r1).append(r2))
                .orElse(Results.empty());
    }
}
