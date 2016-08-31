package com.github.ninerules.rules;

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
        if(object instanceof Violation){
            return equals((Violation)object);
        }
        return false;
    }

    private boolean equals(Violation violation){
        return equals(violation.type)
                && equals(violation.numbers);
    }

    private boolean equals(ViolationType type){
        return Objects.equals(this.type, type);
    }

    private boolean equals(LineCounts numbers){
        return Objects.equals(this.numbers, numbers);
    }
}
