package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;

public class NullParameter implements Parameter<NullParameter> {
    private static final NullParameter INSTANCE = new NullParameter();

    private NullParameter(){
    }

    @Override
    public NullParameter parameter(StrictLevel level) {
        return this;
    }

    public static NullParameter parameter(){
        return INSTANCE;
    }
}
