package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;

/**
 * Rule 1. 1つのメソッドにつき，インデントは n 段階とすること．
 * <ul>
 *   <li>STRICT, GENERAL: n = 1</li>
 *   <li>ROUGHL: n = 2</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class IndentLevel extends IntegerParameter<IndentLevel>{
    public static IndentLevel STRICT_LEVEL = new IndentLevel(1);
    public static IndentLevel GENERAL_LEVEL = STRICT_LEVEL;
    public static IndentLevel ROUGH_LEVEL = new IndentLevel(2);

    public IndentLevel(int indentLevel){
        super(indentLevel);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof IndentLevel){
            return this.isEqualsTo((IndentLevel)object);
        }
        return false;
    }

    
    @Override
    public IndentLevel parameter(StrictLevel level) {
        if(level == StrictLevel.ROUGH){
            return ROUGH_LEVEL;
        }
        return STRICT_LEVEL;
    }
}
