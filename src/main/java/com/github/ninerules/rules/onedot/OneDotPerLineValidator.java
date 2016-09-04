package com.github.ninerules.rules.onedot;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.parameters.DotCount;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.PlainSourceValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class OneDotPerLineValidator extends PlainSourceValidator<DotCount>{
    public static final ViolationType ONE_DOT = new ViolationType("Many dots per line");
    private static final Pattern PATTERN = Pattern.compile("\\.");
    private StringFilter filter = new StringFilter();

    public OneDotPerLineValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void visitLine(String line, LineCount count){
        if(isViolated(line.trim())){
            addViolation(new Violation(ONE_DOT, new LineCounts(count)));
        }
    }

    private boolean isViolated(String line){
        return countDot(filter.filter(line))
                .isGreaterThan(parameter());
    }

    private DotCount countDot(String line){
        return new DotCount(getMatchCount(PATTERN.matcher(line)));
    }

    private int getMatchCount(Matcher matcher){
        int count = 0;
        while(matcher.find()){
            count++;
        }
        return count;
    }

    @Override
    public DotCount parameter() {
        return Parameters.parameter(DotCount.class, level());
    }
}