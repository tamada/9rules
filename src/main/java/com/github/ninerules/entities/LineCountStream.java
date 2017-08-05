package com.github.ninerules.entities;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LineCountStream{
    private LineCountStream(){
        // do nothing.
    }

    public static Stream<LineCount> generate(){
        return IntStream.iterate(1, x -> x + 1)
                .mapToObj(LineCount::new);
    }

    public static Stream<LineCount> of(int... numbers){
        return IntStream.of(numbers)
                .mapToObj(LineCount::new);
    }

    public static Stream<LineCount> range(int startInclusive, int endExclusive){
        return IntStream.range(startInclusive, endExclusive)
                .mapToObj(LineCount::new);
    }

    public static Stream<LineCount> rangeClosed(int startInclusive, int endInclusive){
        return IntStream.rangeClosed(startInclusive, endInclusive)
                .mapToObj(LineCount::new);
    }
}