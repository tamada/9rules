package com.github.ninerules.rules.stringvisitor;

import java.util.stream.Stream;

import com.github.ninerules.entities.LineCount;

class LineCountGenerator {
    public static final Stream<LineCount> generate(){
        return Stream.iterate(1, x -> x + 1)
                .map(LineCount::new);        
    }
}
