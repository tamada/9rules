package com.github.ninerules.rules;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.Target;
import com.github.ninerules.rules.results.Results;

public class Rules {
    private Validators validators;

    public Rules(StrictLevel level){
        ValidatorsBuilder builder = new ValidatorsBuilder(level);
        validators = builder.build();
    }

    public Results validate(final Target unit){
        return validators.validateEach(unit);
    }
}
