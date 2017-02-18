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
    private List<Option> optionList = new ArrayList<>();

    public Options(Stream<Option> stream){
        stream.forEach(optionList::add);
    }

    public boolean isSpecified(Option option){
        return optionList.contains(option);
    }

    Stream<Option> options(){
        return optionList.stream();
    }

    public StrictLevel level(){
        return specifiedLevelOption()
                .map(Option::toLevel)
                .orElse(StrictLevel.STRICT);
    }

    private Optional<Option> specifiedLevelOption(){
        return options().filter(TARGETS::contains)
                .findFirst();
    }
}
