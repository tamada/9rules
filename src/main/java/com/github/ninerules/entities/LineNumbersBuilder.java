package com.github.ninerules.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineNumbersBuilder {
    private List<LineNumber> list = new ArrayList<>();

    private LineNumbersBuilder(){
    }

    public static LineNumbersBuilder create(){
        return new LineNumbersBuilder();
    }
    
    public LineNumbers build(){
        return new LineNumbers(list.stream());
    }

    public LineNumbers build(Stream<LineNumber> stream){
        return new LineNumbers(stream);
    }

    public LineNumbersBuilder range(int from, int to){
        IntStream.range(from, to)
        .forEach(index -> list.add(new LineNumber(index)));

        return this;
    }
    public LineNumbersBuilder of(int... numbers){
        Arrays.stream(numbers)
        .forEach(number -> list.add(new LineNumber(number)));

        return this;
    }
}
