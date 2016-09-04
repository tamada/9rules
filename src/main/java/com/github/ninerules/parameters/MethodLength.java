package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;

/**
 * Rule 6-1. すべてのエンティティを小さくすること．1メソッドはn行まで．
 * <ul>
 *   <li>STRICT: n = 3</li>
 *   <li>GENERAL: n = 5</li>
 *   <li>ROUGHL: n = 10</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class MethodLength extends IntegerParameter<MethodLength>{
    public static MethodLength STRICT_LEVEL = new MethodLength(3);
    public static MethodLength GENERAL_LEVEL = new MethodLength(5);
    public static MethodLength ROUGH_LEVEL = new MethodLength(10);

    public MethodLength(int methodLength){
        super(methodLength);
    }

    public LineCount convertToLineCount(){
        return new LineCount(value);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof MethodLength){
            return this.isEqualsTo((MethodLength)object);
        }
        return false;
    }

    @Override
    public MethodLength parameter(StrictLevel level) {
        if(level == StrictLevel.ROUGH){
            return ROUGH_LEVEL;
        }
        else if(level == StrictLevel.GENERAL){
            return GENERAL_LEVEL;
        }
        return STRICT_LEVEL;
    }
}
