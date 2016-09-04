package com.github.ninerules.parameters;

public abstract class IntegerParameter<T extends IntegerParameter<T>> implements Parameter<T>, Comparable<T> {
    int value;

    public IntegerParameter(int value){
        this.value = value;
    }

    @Override
    public int compareTo(T parameter){
        if(isGreaterThan(parameter)){
            return 1;
        }
        else if(isLessThan(parameter)){
            return -1;
        }
        return 0;
    }

    @Override
    public int hashCode(){
        return value;
    }

    public boolean isEqualsTo(T parameter){
        return value == parameter.value;
    }

    public boolean isGreaterThan(T parameter){
        return value > parameter.value;
    }

    public boolean isLessThan(T parameter){
        return value < parameter.value;
    }
}
