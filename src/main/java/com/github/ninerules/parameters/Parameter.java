package com.github.ninerules.parameters;

import java.io.Serializable;

import com.github.ninerules.entities.Message;

public interface Parameter extends Comparable<Parameter>, Serializable{
    @Override
    default int compareTo(Parameter parameter){
        return new ParameterComparator().compare(this, parameter);
    }

    boolean isEqualTo(Parameter param);

    boolean isLessThan(Parameter param);

    default boolean isGreaterThan(Parameter param){
        return !isLessThan(param) && 
                !isEqualTo(param);
    }

    default String format(Message format){
        return format.toString();
    }
}
