package com.github.ninerules.rules.smallobject;

import java.util.Optional;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.MethodDeclaration;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;
import com.github.ninerules.entities.LineCounts;
import com.github.ninerules.parameters.MethodLength;
import com.github.ninerules.parameters.Parameters;
import com.github.ninerules.rules.JdtValidator;
import com.github.ninerules.rules.Violation;
import com.github.ninerules.rules.ViolationType;

public class MethodLengthValidator extends JdtValidator<MethodLength> {
    public static final ViolationType TOO_LONG_METHOD = new ViolationType("method is too long (over 5 lines).");

    public MethodLengthValidator(StrictLevel level) {
        super(level);
    }

    @Override
    public void endVisit(MethodDeclaration node){
        LineCount difference = countLines(Optional.ofNullable(node.getBody()));
        checkViolationOfMethodLength(node, difference);
        super.endVisit(node);
    }

    private LineCount countLines(Optional<Block> block){
        return block.map(item -> super.countLinesOf(item))
                .orElse(new LineCount(0));
    }

    private void checkViolationOfMethodLength(ASTNode node, LineCount difference){
        if(difference.isGreaterThan(limitLength())){
            this.addViolation(new Violation(TOO_LONG_METHOD, new LineCounts(startLine(node))));
        }
    }

    public LineCount limitLength(){
        return parameter().convertToLineCount();
    }

    @Override
    public MethodLength parameter() {
        return Parameters.parameter(MethodLength.class, level());
    }
}
