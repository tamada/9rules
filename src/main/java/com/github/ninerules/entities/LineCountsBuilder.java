package com.github.ninerules.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LineCountsBuilder {
    private List<LineCount> list = new ArrayList<>();

    private LineCountsBuilder(){
        //  do nothing.
    }

    public static  LineCountsBuilder builder(){
        return new LineCountsBuilder();
    }

    public LineCounts build(){
        LineCount[] array = list.stream()
                .toArray(LineCount[]::new);
        return new LineCounts(array);
    }

    public LineCountsBuilder stream(Stream<LineCount> stream){
        stream.forEach(list::add);
        return this;
    }

    public LineCountsBuilder range(int from, int to){
        LineCountStream.range(from, to)
        .forEach(list::add);
        return this;
    }

    public LineCountsBuilder rangeClosed(int startInclusive, int endInclusive){
        LineCountStream.rangeClosed(startInclusive, endInclusive)
        .forEach(list::add);
        return this;
    }

    public LineCountsBuilder of(int... numbers){
        LineCountStream.of(numbers)
        .forEach(list::add);
        return this;
    }
}
