package com.github.ninerules.parameters;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.github.ninerules.StrictLevel;

public class Parameters {
    private static final String STRICT_LEVEL_FIELD_NAME = "STRICT_LEVEL";
    private static final String GENERAL_LEVEL_FIELD_NAME = "GENERAL_LEVEL";
    private static final String ROUGH_LEVEL_FIELD_NAME = "ROUGH_LEVEL";
    private static final Parameters INSTANCE = new Parameters();
    private Map<StrictLevel, String> levels = new HashMap<>();

    private Parameters(){
        levels.put(StrictLevel.STRICT,  STRICT_LEVEL_FIELD_NAME);
        levels.put(StrictLevel.GENERAL, GENERAL_LEVEL_FIELD_NAME);
        levels.put(StrictLevel.ROUGH,   ROUGH_LEVEL_FIELD_NAME);
    }

    public static <T> T parameter(Class<? extends T> clazz, StrictLevel level){
        try {
            return INSTANCE.<T>createParameter(clazz, level);
        } catch (NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private <T> T createParameter(Class<? extends T> clazz, StrictLevel level)
            throws IllegalAccessException, NoSuchFieldException, SecurityException{
        Field field = clazz.getField(levelString(level));
        return (T)field.get(null);
    }

    private String levelString(StrictLevel level){
        return levels.get(level);
    }
}
