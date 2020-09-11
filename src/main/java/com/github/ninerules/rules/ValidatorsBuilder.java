package com.github.ninerules.rules;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.utils.ServiceLoader;
import com.github.ninerules.utils.ServiceLoaderBuilder;
import io.vavr.control.Try;

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
        serviceLoaderStream().map(this::createInstance)
        .forEach(item -> item.ifPresent(validators::register));
    }
    
    private Stream<Class<Validator>> serviceLoaderStream(){
        ServiceLoader<Validator> serviceLoader = createLoader(Validator.class);
        return serviceLoader.stream();
    }

    private Optional<Validator> createInstance(Class<Validator> clazz){
        return Try.of(() -> instantiate(clazz, level))
                .toJavaOptional();
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
