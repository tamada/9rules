package com.github.ninerules.parameters;

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
public class DotCount extends IntegerParameter{
    private static final long serialVersionUID = -1461125148076263772L;

    public static final DotCount STRICT_LEVEL = new DotCount(1);
    public static final DotCount GENERAL_LEVEL = new DotCount(2);
    public static final DotCount ROUGH_LEVEL = new DotCount(3);

    public DotCount(int dotCount){
        super(dotCount);
    }
}
