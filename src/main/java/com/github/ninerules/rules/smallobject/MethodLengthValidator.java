package com.github.ninerules.rules.smallobject;

import java.util.Optional;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.entities.Message;
import com.github.ninerules.parameters.MethodLength;
import com.github.ninerules.rules.jdtvisitor.JdtValidator;

public class MethodLengthValidator extends JdtValidator {
    public static final Message TOO_LONG_METHOD = new Message("method is too long (over %s lines).");

    public MethodLengthValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(MethodDeclaration node){
        LineCount difference = counts(node.getBody());
        checkViolationOfMethodLength(node, difference);
        super.endVisit(node);
    }

    private LineCount counts(Block block){
        return Optional.ofNullable(block)
                .map(super::countLinesOf)
                .orElse(new LineCount(0));
    }

    private void checkViolationOfMethodLength(MethodDeclaration node, LineCount difference){
        if(difference.isGreaterThan(limitLength()))
            this.addViolation(buildViolation(TOO_LONG_METHOD, new LineCounts(startLine(node))));
    }

    public LineCount limitLength(){
        return ((MethodLength)parameter()).convertToLineCount();
    }

    @Override
    public Class<MethodLength> parameterClass() {
        return MethodLength.class;
    }
}
