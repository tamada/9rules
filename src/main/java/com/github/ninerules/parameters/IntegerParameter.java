package com.github.ninerules.parameters;

import java.util.Objects;

abstract class IntegerParameter implements Parameter{
    int value;

    public IntegerParameter(int value){
        this.value = value;
    }

    @Override
    public int hashCode(){
        return value;
    }

    @Override
    public boolean equals(Object object){
        Class<?> clazz1 = getClass();
        Class<?> clazz2 = object.getClass();
        return Objects.equals(clazz1, clazz2) && isEqualsTo((Parameter)object);
    }

    @Override
    public boolean isEqualsTo(Parameter parameter){
        return value == ((IntegerParameter)parameter).value;
    }

    @Override
    public boolean isGreaterThan(Parameter parameter){
        return value > ((IntegerParameter)parameter).value;
    }

    @Override
    public boolean isLessThan(Parameter parameter){
        return value < ((IntegerParameter)parameter).value;
    }
}
