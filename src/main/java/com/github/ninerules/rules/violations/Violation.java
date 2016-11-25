package com.github.ninerules.rules.violations;

import java.util.Objects;

import com.github.ninerules.entities.LineCounts;

public class Violation {
    private ViolationType type;
    private LineCounts numbers;

    public Violation(ViolationType type, LineCounts numbers){
        this.type = type;
        this.numbers = numbers;
    }

    @Override
    public String toString(){
        return String.format("line: %s, %s", numbers, type);
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Violation
                && equals((Violation)object);
    }

    private boolean equals(Violation violation){
        return equals(violation.type)
                && equals(violation.numbers);
    }

    private boolean equals(ViolationType type2){
        return type.equals(type2);
    }

    private boolean equals(LineCounts numbers){
        return Objects.equals(this.numbers, numbers);
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(this);
    }
}
