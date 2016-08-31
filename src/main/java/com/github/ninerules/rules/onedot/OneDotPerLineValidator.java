package com.github.ninerules.rules.onedot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.PlainSourceValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class OneDotPerLineValidator extends PlainSourceValidator{
    public static final ViolationType ONE_DOT = new ViolationType("Many dots per line");
    private static final Pattern PATTERN = Pattern.compile("\\.");

    @Override
    public void visit(String line, LineCount count){
        if(isViolated(line.trim())){
            addViolation(new Violation(ONE_DOT, new LineCounts(count)));
        }
    }

    private boolean isViolated(String line){
        if(line.startsWith("import")
                || line.startsWith("package")){
            return false;
        }
        return countDot(filterString(line)) > 1;
    }

    private String filterString(String line){
        return line.replaceAll("this\\.", "")  // this. は削除する．
                .replaceAll("\".*\"", "\"\"")  // 文字列は空文字列にする．
                .replaceAll("^\\.", "")        // 行頭のドットは削除する． 
                .replaceAll("\\.\\.\\. ", " ") // varargsの...を削除する．
                .replaceAll("System\\.out\\.print", "print")
                .trim();
    }

    private int countDot(String line){
        return getMatchCount(PATTERN.matcher(line));
    }

    private int getMatchCount(Matcher matcher){
        int count = 0;
        while(matcher.find()){
            count++;
        }
        return count;
    }
}