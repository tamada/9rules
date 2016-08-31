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

    public static LineCountsBuilder builder(){
        return new LineCountsBuilder();
    }
    
    public LineCounts build(){
        LineCount[] array = list.stream()
                .toArray(size -> new LineCount[size]);
        return new LineCounts(array);
    }

    public static LineCounts build(int... numbers){
        return LineCountsBuilder.builder()
                .of(numbers).build();
    }

    public LineCounts build(Stream<LineCount> stream){
        return new LineCounts(stream.toArray(size -> new LineCount[size]));
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
