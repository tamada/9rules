package com.github.ninerules.rules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.utils.Either;
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

    // TODO: 2020/07/25 if this method has some error, how to notify it to the user? 
    private void initialize(final Validators validators){
        serviceLoaderStream().map(this::createInstance)
                .flatMap(either -> either.stream())
                .forEach(validators::register);
    }
    
    private Stream<Class<Validator>> serviceLoaderStream(){
        ServiceLoader<Validator> serviceLoader = createLoader(Validator.class);
        return serviceLoader.stream();
    }

    private Either<Exception, Validator> createInstance(Class<Validator> clazz){
        try{
            return Either.ofValue(instantiate(clazz, level));
        } catch(Exception e) {
            return Either.ofException(e);
        }
    }

    private Validator instantiate(Class<Validator> clazz, StrictLevel level)
            throws NoSuchMethodException, InstantiationException, IllegalAccessException,
                   InvocationTargetException {
        Class<StrictLevel> classOfStrictLevel = StrictLevel.class;
        Constructor<Validator> constructor = clazz.getConstructor(classOfStrictLevel);
        return constructor.newInstance(level);
    }

    private ServiceLoader<Validator> createLoader(Class<Validator> clazz){
        return new ServiceLoaderBuilder<Validator>().load(clazz);
    }
}
