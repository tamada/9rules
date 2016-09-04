package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;
import com.github.ninerules.entities.LineCount;

/**
 * Rule 6-1. すべてのエンティティを小さくすること．1クラスはn行まで．
 * <ul>
 *   <li>STRICT: n = 50</li>
 *   <li>GENERAL: n = 70</li>
 *   <li>ROUGHL: n = 100</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class SourceLength extends IntegerParameter<SourceLength>{
    public static SourceLength STRICT_LEVEL = new SourceLength(50);
    public static SourceLength GENERAL_LEVEL = new SourceLength(70);
    public static SourceLength ROUGH_LEVEL = new SourceLength(100);

    public SourceLength(int sourceLength){
        super(sourceLength);
    }

    public LineCount convertToLineCount(){
        return new LineCount(value);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof SourceLength){
            return this.isEqualsTo((SourceLength)object);
        }
        return false;
    }

    @Override
    public SourceLength parameter(StrictLevel level) {
        if(level == StrictLevel.ROUGH){
            return ROUGH_LEVEL;
        }
        else if(level == StrictLevel.GENERAL){
            return GENERAL_LEVEL;
        }
        return STRICT_LEVEL;
    }
}
