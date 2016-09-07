package com.github.ninerules.parameters;

public interface Parameter extends Comparable<Parameter>{
    default int compareTo(Parameter parameter){
        if(isLessThan(parameter)) return -1;
        else if(isGreaterThan(parameter)) return 1;
        return 0;
    }

    boolean isEqualsTo(Parameter param);

    boolean isLessThan(Parameter param);

    boolean isGreaterThan(Parameter param);
}
