package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;

public interface Parameter<T> {
    public T parameter(StrictLevel level);
}
