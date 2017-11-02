package com.github.ninerules.rules.onedot;

import java.util.regex.Pattern;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.DotCount;
import com.github.ninerules.parameters.Parameter;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.stringvisitor.PlainSourceValidator;

public class OneDotPerLineValidator extends PlainSourceValidator{
    public static final Message ONE_DOT = new Message("many dots per line (more than %s dots).");
    private static final Pattern PATTERN = Pattern.compile("\\.");

    private StringFilterManager filter = new StringFilterManager();

    public OneDotPerLineValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void visitLine(String line, LineCount count){
        if(isViolated(line.trim()))
            addViolation(buildViolation(ONE_DOT, new LineCounts(count)));
    }

    private boolean isViolated(String line){
        return countDot(filter.filter(line))
                .isGreaterThan(parameter());
    }

    private DotCount countDot(String line){
        return new DotCount(getMatchCount(line));
    }

    private int getMatchCount(String line){
        MatchingCounter counter = new MatchingCounter(PATTERN);
        return counter.apply(line);
    }

    @Override
    public Parameter parameter() {
        return Parameters
                .parameter(DotCount.class, level());
    }
}