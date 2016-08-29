package com.github.ninerules.rules;

import com.github.ninerules.entities.LineNumbers;

public class Violation {
    private ViolationType type;
    private LineNumbers numbers;

    public Violation(ViolationType type, LineNumbers numbers){
        this.type = type;
        this.numbers = numbers;
    }

    @Override
    public String toString(){
        return String.format("line: %s, %s%n", numbers, type);
    }
}
