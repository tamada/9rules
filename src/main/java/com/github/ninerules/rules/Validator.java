package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.rules.results.Results;

public interface Validator<T> {
    Parameter parameter();

    StrictLevel level();

    void addViolation(Violation violation);

    Results createResults(FileName fileName);
}
