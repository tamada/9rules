package com.github.nine.rules;

import com.github.nine.entities.LineNumbers;

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
