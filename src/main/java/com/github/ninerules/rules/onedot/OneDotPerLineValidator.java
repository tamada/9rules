package com.github.ninerules.rules.onedot;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class OneDotPerLineValidator extends Validator {
    public static final ViolationType ONE_DOT = new ViolationType("Many dots per line");
    private static final Pattern PATTERN = Pattern.compile("\\.");

    @Override
    public boolean visit(MethodDeclaration node){
        checkViolation(node);
        return super.visit(node);
    }

    private void checkViolation(MethodDeclaration node){
        if(isViolated(node)){
            addViolation(new Violation(ONE_DOT, new LineCounts(startLine(node))));
        }
    }

    private boolean isViolated(MethodDeclaration node){
        return codeStream(node).map(line -> filterString(line))
                .mapToInt(line -> countDot(line))
                .max()
                .orElse(0) > 1;
    }

    private String filterString(String line){
        return line.replaceAll("this.", "")
                .replaceAll("System.out.print", "print")
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

    private Stream<String> codeStream(MethodDeclaration node){
        String code = node.toString();
        String[] lines = code.split("\n");
        return Arrays.stream(lines);
    }
}
