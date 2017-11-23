package com.github.ninerules.entities;

import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;

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
                && checkEquals((Violation)object);
    }

    private boolean checkEquals(@NonNull Violation violation){
        return checkEquals(violation.type)
                && checkEquals(violation.numbers);
    }

    private boolean checkEquals(ViolationType type2){
        return type.equals(type2);
    }

    private boolean checkEquals(LineCounts numbers){
        return this.numbers.equals(numbers);
    }

    @Override
    public int hashCode(){
        return Objects.hash(type, numbers);
    }
}
