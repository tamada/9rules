package com.github.ninerules.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineCounts {
    private List<LineCount> list = new ArrayList<>();

    public LineCounts(LineCount... numbers){
        this(Arrays.stream(numbers));
    }

    LineCounts(Stream<LineCount> stream){
        stream.sorted()
        .forEach(item -> list.add(item));
    }

    @Override
    public String toString(){
        return list.stream()
                .map(number -> number.toString())
                .collect(Collectors.joining(","));
    }
}
