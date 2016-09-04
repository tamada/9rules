package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;

/**
 * Rule 4. 1行につき，ドットは n 個までにすること．
 * <ul>
 *   <li>STRICT: n = 1</li>
 *   <li>GENERAL: n = 2</li>
 *   <li>ROUGHL: n = 3</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class DotCount extends IntegerParameter<DotCount>{
    public static DotCount STRICT_LEVEL = new DotCount(1);
    public static DotCount GENERAL_LEVEL = new DotCount(2);
    public static DotCount ROUGH_LEVEL = new DotCount(3);

    public DotCount(int dotCount){
        super(dotCount);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof DotCount){
            return this.isEqualsTo((DotCount)object);
        }
        return false;
    }

    @Override
    public DotCount parameter(StrictLevel level) {
        if(level == StrictLevel.ROUGH){
            return ROUGH_LEVEL;
        }
        else if(level == StrictLevel.GENERAL){
            return GENERAL_LEVEL;
        }
        return STRICT_LEVEL;
    }
}
