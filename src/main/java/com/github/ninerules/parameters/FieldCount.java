package com.github.ninerules.parameters;

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
public class FieldCount extends IntegerParameter{
    private static final long serialVersionUID = 3498119176664452012L;

    public static FieldCount STRICT_LEVEL = new FieldCount(2);
    public static FieldCount GENERAL_LEVEL = new FieldCount(3);
    public static FieldCount ROUGH_LEVEL = new FieldCount(4);

    public FieldCount(int fieldCount){
        super(fieldCount);
    }
}
