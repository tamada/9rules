package com.github.ninerules.rules;

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
}
