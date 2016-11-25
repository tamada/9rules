package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.ViolationType;

public interface Validator {
    void addViolation(Violation violation);

    default Violation buildViolation(Message type, LineCounts counts) {
        return new Violation(buildViolationType(type), counts);
    }

    default ViolationType buildViolationType(Message message) {
        return new ViolationType(message, parameter());
    }

    Results createResults(FileName fileName);

    StrictLevel level();

    Parameter parameter();
}
