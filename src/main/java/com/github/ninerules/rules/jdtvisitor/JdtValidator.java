package com.github.ninerules.rules.jdtvisitor;

import java.util.Objects;

import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.Name;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.FileName;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.results.Results;
import com.github.ninerules.rules.violations.DefaultViolations;
import com.github.ninerules.rules.violations.EmptyViolations;
import com.github.ninerules.rules.violations.Violation;
import com.github.ninerules.rules.violations.Violations;

public abstract class JdtValidator extends ASTVisitorPlus implements Validator{
    private Violations holder = new DefaultViolations();

    public JdtValidator(StrictLevel level){
        super(level);
    }

    @Override
    public boolean visit(MarkerAnnotation annotation){
        ignoreIfTypeIsIgnoreRule(annotation.getTypeName());
        return super.visit(annotation);
    }

    @Override
    public void addViolation(Violation violation){
        holder.add(violation);
    }

    @Override
    public final Results createResults(FileName fileName){
        return holder.createResults(fileName);
    }

    private void ignoreIfTypeIsIgnoreRule(Name name){
        ignoreIfTypeIsIgnoreRule(name.getFullyQualifiedName());
    }

    private void ignoreIfTypeIsIgnoreRule(String name){
        if(Objects.equals("IgnoreRules", name))
            holder = new EmptyViolations();
    }
}
