package com.github.nine.entities;

public class LineNumber implements Comparable<LineNumber>{
    private int lineNumber;

    public LineNumber(int linenumber){
        if(linenumber < 0){
            throw new IllegalArgumentException("no negative line number: " + linenumber);
        }
        this.lineNumber = linenumber;
    }

    @Override
    public int compareTo(LineNumber number) {
        return lineNumber - number.lineNumber;
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof LineNumber){
            return lineNumber == ((LineNumber)object).lineNumber;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return lineNumber;
    }

    public boolean isSequent(LineNumber number){
        int difference = lineNumber - number.lineNumber;
        if(Math.abs(difference) <= 1){
            return true;
        }
        return false;
    }

    public String toString(){
        return String.valueOf(lineNumber);
    }
}
