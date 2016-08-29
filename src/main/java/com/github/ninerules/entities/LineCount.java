package com.github.ninerules.entities;

public class LineCount implements Comparable<LineCount>{
    private int lineNumber;

    public LineCount(int linenumber){
        if(linenumber < 0){
            throw new IllegalArgumentException("no negative line number: " + linenumber);
        }
        this.lineNumber = linenumber;
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
        if(object instanceof LineCount){
            return lineNumber == ((LineCount)object).lineNumber;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return lineNumber;
    }

    public LineCount difference(LineCount line){
        return new LineCount(Math.abs(line.lineNumber - lineNumber));
    }

    public boolean isSequent(LineCount number){
        int difference = lineNumber - number.lineNumber;
        return Math.abs(difference) <= 1;
    }

    public String toString(){
        return String.valueOf(lineNumber);
    }
}