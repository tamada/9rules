package com.github.ninerules.cli;

import static com.github.ninerules.StrictLevel.STRICT;
import static com.github.ninerules.StrictLevel.GENERAL;
import static com.github.ninerules.StrictLevel.ROUGH;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.github.ninerules.StrictLevel;

public class Option {
    public static final Option HELP_OPTION    = new Option("--help");
    public static final Option STRICT_OPTION  = new Option("--strict");
    public static final Option GENERAL_OPTION = new Option("--general");
    public static final Option ROUGH_OPTION   = new Option("--rough");
    private static final Map<Option, StrictLevel> LEVELS = new HashMap<>();

    private String optionName;

    static{
        LEVELS.put(STRICT_OPTION,  STRICT);
        LEVELS.put(GENERAL_OPTION, GENERAL);
        LEVELS.put(ROUGH_OPTION,   ROUGH);
    }

    public Option(String optionName){
        this.optionName = optionName;
    }

    public StrictLevel toLevel(){
        return LEVELS.getOrDefault(this, STRICT);
    }

    @Override
    public int hashCode(){
        return optionName.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Option
                && equals((Option)object);
    }

    private boolean equals(Option option){
        String name = option.optionName;
        return Objects.equals(optionName, name);
    }
}
