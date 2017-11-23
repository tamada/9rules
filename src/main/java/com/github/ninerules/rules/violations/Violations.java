package com.github.ninerules.rules.violations;

import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.Violation;
import com.github.ninerules.rules.results.Results;

public interface Violations {
    void add(Violation violation);

    Results createResults(FileName name);
}
