package com.github.ninerules.cli;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import com.github.ninerules.StrictLevel;

public class Options {
    private static final List<Option> TARGETS = Arrays.asList(
            Option.STRICT_OPTION,
            Option.GENERAL_OPTION,
            Option.ROUGH_OPTION);
    private List<Option> options = new ArrayList<>();

    public Options(Stream<Option> stream){
        stream.forEach(item -> {
            options.add(item);
        });
    }

    public boolean isSpecified(Option option){
        return options.contains(option);
    }

    Stream<Option> options(){
        return options.stream();
    }

    public StrictLevel level(){
        return specifiedLevelOption()
                .map(option -> option.toLevel())
                .orElse(StrictLevel.STRICT);
    }

    private Optional<Option> specifiedLevelOption(){
        return options()
                .filter(option -> TARGETS.contains(option))
                .findFirst();
    }
}
