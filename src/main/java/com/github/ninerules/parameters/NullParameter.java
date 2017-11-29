package com.github.ninerules.parameters;

public class NullParameter implements Parameter {
    private static final long serialVersionUID = 3221767821939834819L;

    private static final NullParameter INSTANCE = new NullParameter();
    public static final NullParameter STRICT_LEVEL = INSTANCE;
    public static final NullParameter GENERAL_LEVEL = INSTANCE;
    public static final NullParameter ROUGH_LEVEL = INSTANCE;

    private NullParameter(){
    }

    public static final NullParameter parameter(){
        return INSTANCE;
    }

    @Override
    public boolean isEqualTo(Parameter parameter) {
        return this == parameter;
    }

    @Override
    public boolean isLessThan(Parameter parameter) {
        return false;
    }

    @Override
    public boolean isGreaterThan(Parameter parameter) {
        return false;
    }

    @Override
    public boolean equals(Object object){
        return this == object;
    }

    @Override
    public int hashCode(){
        return 1;
    }
}
