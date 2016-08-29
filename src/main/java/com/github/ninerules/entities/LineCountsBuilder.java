package com.github.ninerules.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineCountsBuilder {
    private List<LineCount> list = new ArrayList<>();

    private LineCountsBuilder(){
    }

    public static LineCountsBuilder create(){
        return new LineCountsBuilder();
    }
    
    public LineCounts build(){
        return new LineCounts(list.stream());
    }

    public LineCounts build(Stream<LineCount> stream){
        return new LineCounts(stream);
    }

    public LineCountsBuilder range(int from, int to){
        IntStream.range(from, to)
        .forEach(index -> list.add(new LineCount(index)));

        return this;
    }
    public LineCountsBuilder of(int... numbers){
        Arrays.stream(numbers)
        .forEach(number -> list.add(new LineCount(number)));

        return this;
    }
}
