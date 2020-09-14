package com.github.ninerules.parameters;

import static com.github.ninerules.StrictLevel.GENERAL;
import static com.github.ninerules.StrictLevel.ROUGH;
import static com.github.ninerules.StrictLevel.STRICT;

import java.lang.reflect.Field;
import java.util.EnumMap;
import java.util.Map;

import com.github.ninerules.StrictLevel;
import io.vavr.control.Try;

public class Parameters {
    private static final String STRICT_LEVEL_FIELD_NAME = "STRICT_LEVEL";
    private static final String GENERAL_LEVEL_FIELD_NAME = "GENERAL_LEVEL";
    private static final String ROUGH_LEVEL_FIELD_NAME = "ROUGH_LEVEL";
    private static final Parameters INSTANCE = new Parameters();
    private Map<StrictLevel, String> levels = new EnumMap<>(StrictLevel.class);

    private Parameters(){
        levels.put(STRICT,  STRICT_LEVEL_FIELD_NAME);
        levels.put(GENERAL, GENERAL_LEVEL_FIELD_NAME);
        levels.put(ROUGH,   ROUGH_LEVEL_FIELD_NAME);
    }

    public static <T> T parameter(Class<? extends T> clazz, StrictLevel level){
        return Try.of(
                () -> INSTANCE.createParameter(clazz, level))
                .getOrElseThrow(() -> new InternalError());
    }

    @SuppressWarnings("unchecked")
    private <T> T createParameter(Class<? extends T> clazz, StrictLevel level)
            throws IllegalAccessException, NoSuchFieldException{
        Field field = clazz.getField(levelString(level));
        return (T)field.get(null);
    }

    private String levelString(StrictLevel level){
        return levels.get(level);
    }
}
