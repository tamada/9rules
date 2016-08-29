package com.github.ninerules.fcc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;

import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.LineCountsBuilder;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.Validator;
import com.github.ninerules.rules.ViolationType;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class FirstClassCollectionValidator extends Validator{
    public static final ViolationType FCC = new ViolationType("not first class collection.");
    private List<FieldDeclaration> list = new ArrayList<>();

    @Override
    public boolean visit(FieldDeclaration node) {
        if(!isStaticAndFinal(node)){
            list.add(node);
        }
        return super.visit(node);
    }

    @Override
    public void endVisit(CompilationUnit node) {
        boolean containsCollection = containsCollectionField(list);
        if(containsCollection && list.size() > 1){
            addViolation(new Violation(FCC, getLineNumbers()));
        }
        super.endVisit(node);
    }

    private LineCounts getLineNumbers(){
        return LineCountsBuilder.create().build(list.stream()
                .map(declaration -> startLine(declaration)));
    }

    private boolean isStaticAndFinal(FieldDeclaration node){
        @SuppressWarnings("unchecked")
        List<Modifier> modifiers = node.modifiers();
        return modifiers.stream()
                .filter(modifier -> isStaticOrFinal(modifier))
                .collect(Collectors.counting()) == 2;
    }

    private boolean isStaticOrFinal(Modifier modifier){
        return modifier.isFinal() || modifier.isStatic();        
    }

    private boolean containsCollectionField(List<FieldDeclaration> list){
        return list.stream()
                .filter(item -> isCollection(item))
                .collect(Collectors.counting()) > 0;
    }

    private boolean isCollection(FieldDeclaration field){
        Type fieldType = field.getType();
        String type = fieldType.toString();
        
        return fieldType.isArrayType()
                || type.matches("(java.util.)?([A-Z][a-z]+)?List(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Deque(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Queue(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Set(<.*>)?")
                || type.matches("(java.util.)?([A-Z][a-z]+)?Map(<.*, .*>)?");
                
    }
}
