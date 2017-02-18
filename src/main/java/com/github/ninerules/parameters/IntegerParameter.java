package com.github.ninerules.parameters;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

import com.github.ninerules.entities.Message;

abstract class IntegerParameter implements Parameter{
    private static final long serialVersionUID = 9066650843731685566L;

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
        return object instanceof Parameter && checkEquals((Parameter)object);
    }

    private boolean checkEquals(@NonNull Parameter parameter){
        Class<?> clazz1 = getClass();
        Class<?> clazz2 = parameter.getClass();
        return Objects.equals(clazz1, clazz2) && isEqualsTo(parameter);
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

    @Override
    public String format(Message message){
        return message.format(value);
    }
}
