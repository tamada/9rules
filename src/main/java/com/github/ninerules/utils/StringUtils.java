package com.github.ninerules.utils;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class StringUtils {
    private StringUtils(){
    }

    public static boolean isEmptyString(String string){
        return string.length() == 0;
    }

    public static <T> Optional<T> ifNotEmpty(String string, Function<String, T> function){
        Optional<String> optional = optional(string);
        return optional.map(function);
    }

    public static Optional<String> optional(String string){
        return !Objects.equals(string,  "")
                ? Optional.of(string)
                : Optional.empty();
    }
}
