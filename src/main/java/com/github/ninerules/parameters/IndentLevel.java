package com.github.ninerules.parameters;

/**
 * Rule 1. 1つのメソッドにつき，インデントは n 段階とすること．
 * <ul>
 *   <li>STRICT, GENERAL: n = 1</li>
 *   <li>ROUGHL: n = 2</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class IndentLevel extends IntegerParameter{
    private static final long serialVersionUID = -4712495670034046431L;

    public static final IndentLevel STRICT_LEVEL = new IndentLevel(1);
    public static final IndentLevel GENERAL_LEVEL = STRICT_LEVEL;
    public static final IndentLevel ROUGH_LEVEL = new IndentLevel(2);

    public IndentLevel(int indentLevel){
        super(indentLevel);
    }
}
