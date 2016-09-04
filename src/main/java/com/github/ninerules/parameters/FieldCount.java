package com.github.ninerules.parameters;

import com.github.ninerules.StrictLevel;

/**
 * Rule 7. １つのクラスにつき，インスタンス変数は n 個までにすること．
 * <ul>
 *   <li>STRICT: n = 2</li>
 *   <li>GENERAL: n = 3</li>
 *   <li>ROUGHL: n = 4</li>
 * </ul>
 *
 * @author Haruaki Tamada
 */
public class FieldCount extends IntegerParameter<FieldCount>{
    public static FieldCount STRICT_LEVEL = new FieldCount(2);
    public static FieldCount GENERAL_LEVEL = new FieldCount(3);
    public static FieldCount ROUGH_LEVEL = new FieldCount(4);

    public FieldCount(int fieldCount){
        super(fieldCount);
    }

    @Override
    public boolean equals(Object object){
        if(object instanceof FieldCount){
            return this.isEqualsTo((FieldCount)object);
        }
        return false;
    }

    @Override
    public FieldCount parameter(StrictLevel level) {
        if(level == StrictLevel.ROUGH){
            return ROUGH_LEVEL;
        }
        else if(level == StrictLevel.GENERAL){
            return GENERAL_LEVEL;
        }
        return STRICT_LEVEL;
    }
}
