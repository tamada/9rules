package com.github.ninerules.rules;

import java.lang.reflect.Constructor;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.utils.ExceptionHandler;
import com.github.ninerules.utils.ServiceLoader;
import com.github.ninerules.utils.ServiceLoaderBuilder;

public class ValidatorsBuilder{
    private StrictLevel level;

    public ValidatorsBuilder(StrictLevel level){
        this.level = level;
    }

    public Validators build(){
        Validators validators = new Validators();
        initialize(validators);
        return validators;
    }

    private void initialize(final Validators validators){
        serviceLoaderStream()
        .map(clazz -> createInstance(clazz, level))
        .forEach(item -> validators.register(item));
    }
    
    private Stream<Class<Validator>> serviceLoaderStream(){
        ServiceLoader<Validator> serviceLoader = createLoader(Validator.class);
        return serviceLoader.stream();
    }

    private Validator createInstance(Class<Validator> clazz, StrictLevel level){
        return ExceptionHandler.performOrThrows(clazz, level, null, 
                (targetClass, strictLevel) -> instantiate(targetClass, strictLevel));
    }

    private Validator instantiate(Class<Validator> clazz, StrictLevel level) throws Exception{
        Class<StrictLevel> classOfStrictLevel = StrictLevel.class;
        Constructor<Validator> constructor = clazz.getConstructor(classOfStrictLevel);
        return constructor.newInstance(level);
    }

    private ServiceLoader<Validator> createLoader(Class<Validator> clazz){
        return new ServiceLoaderBuilder<Validator>().load(clazz);
    }
}
