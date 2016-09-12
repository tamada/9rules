package com.github.ninerules.rules;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jdt.core.dom.FieldDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.LineCountsBuilder;

public abstract class FieldCollectingValidator extends JdtValidator{
    protected List<FieldDeclaration> list = new ArrayList<>();

    public FieldCollectingValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public boolean visit(FieldDeclaration node) {
        list.add(node);
        return super.visit(node);
    }

    public long computesFieldCount(Predicate<FieldDeclaration> predict){
        return list.stream()
                .filter(predict)
                .collect(Collectors.counting());
    }

    public LineCounts lineNumbers(Predicate<FieldDeclaration> predicate){
        return LineCountsBuilder.build(builder -> 
            builder.stream(stream(predicate))
        );
    }

    private Stream<LineCount> stream(Predicate<FieldDeclaration> predicate){
        return list.stream()
                .filter(predicate)
                .map(declaration -> startLine(declaration));
    }
}