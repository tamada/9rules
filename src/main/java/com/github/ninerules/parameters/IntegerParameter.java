package com.github.ninerules.parameters;

import java.util.Objects;

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
        return object instanceof IntegerParameter && Objects.equals(
                        getClass(), object.getClass())
                && isEqualTo((Parameter)object);
    }

    @Override
    public boolean isEqualTo(Parameter parameter){
        return value == ((IntegerParameter)parameter).value;
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
