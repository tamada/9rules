package com.github.nine.fcc;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.Type;

import com.github.nine.entities.LineNumbers;
import com.github.nine.entities.LineNumbersBuilder;
import com.github.nine.rules.Violation;
import com.github.nine.rules.ViolationChecker;
import com.github.nine.rules.ViolationType;

/**
 * First class collection violation checker.
 * 
 * @author Haruaki Tamada
 */
public class FCCVisitor extends ViolationChecker{
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

    private LineNumbers getLineNumbers(){
        return LineNumbersBuilder.create().build(list.stream()
                .map(declaration -> getLineNumber(declaration.getStartPosition())));
    }

    private boolean isStaticAndFinal(FieldDeclaration node){
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
