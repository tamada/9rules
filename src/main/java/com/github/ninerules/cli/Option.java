package com.github.ninerules.cli;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;
import com.github.ninerules.StrictLevel;

public class Option {
    private static final Map<Option, StrictLevel> LEVELS = new HashMap<>();

    public static final Option HELP_OPTION    = new Option("--help");
    public static final Option STRICT_OPTION  = new Option("--strict",  StrictLevel.STRICT);
    public static final Option GENERAL_OPTION = new Option("--general", StrictLevel.GENERAL);
    public static final Option ROUGH_OPTION   = new Option("--rough",   StrictLevel.ROUGH);

    private String optionName;

    private Option(String label, StrictLevel level){
        this(label);
        LEVELS.put(this, level);
    }

    public Option(String optionName){
        this.optionName = optionName;
    }

    public StrictLevel toLevel(){
        return LEVELS.getOrDefault(
                this, StrictLevel.STRICT);
    }

    @Override
    public int hashCode(){
        return optionName.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return object instanceof Option 
                && checkEquals((Option)object);
    }

    private boolean checkEquals(@NonNull Option option){
        String name = option.optionName;
        return Objects.equals(optionName, name);
    }
}
