package com.github.ninerules.entities;

public class LineCount implements Comparable<LineCount>{
    private int lineNumber;

    public LineCount(int lineNumber){
        checkArgument(lineNumber);
        this.lineNumber = lineNumber;
    }

    private void checkArgument(int lineNumber){
        if(lineNumber < 0){
            throw new IllegalArgumentException("no negative line number: " + lineNumber);
        }
    }

    public boolean isGreaterThan(LineCount number){
        return this.compareTo(number) > 0;
    }

    @Override
    public int compareTo(LineCount number) {
        return lineNumber - number.lineNumber;
    }

    @Override
    public boolean equals(Object object){
        return object instanceof LineCount
                && lineNumber == ((LineCount)object).lineNumber;
    }

    @Override
    public int hashCode(){
        return lineNumber;
    }

    public LineCount difference(LineCount line){
        int difference = line.lineNumber - lineNumber;
        return new LineCount(Math.abs(difference));
    }

    public boolean isSequent(LineCount number){
        int difference = lineNumber - number.lineNumber;
        return Math.abs(difference) <= 1;
    }

    @Override
    public String toString(){
        return String.valueOf(lineNumber);
    }
}