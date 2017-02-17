package com.github.ninerules.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineCountsBuilder {
    private List<LineCount> list = new ArrayList<>();

    private LineCountsBuilder(){
    }

    public static LineCounts build(Consumer<LineCountsBuilder> block){
        LineCountsBuilder builder = new LineCountsBuilder();
        block.accept(builder);
        return builder.build();
    }

    private LineCounts build(){
        LineCount[] array = list.stream()
                .toArray(LineCount[]::new);
        return new LineCounts(array);
    }

    public LineCountsBuilder stream(Stream<LineCount> stream){
        stream.forEach(list::add);
        return this;
    }

    public LineCountsBuilder range(int from, int to){
        IntStream.range(from, to)
        .mapToObj(LineCount::new).forEach(list::add);
        return this;
    }

    public LineCountsBuilder of(int... numbers){
        Arrays.stream(numbers)
        .mapToObj(LineCount::new).forEach(list::add);
        return this;
    }
}
