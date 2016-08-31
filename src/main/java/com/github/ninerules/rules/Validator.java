package com.github.ninerules.rules;

import com.github.ninerules.entities.FileName;

public interface Validator {
    public void addViolation(Violation violation);

    public Results createResults(FileName fileName);
}
