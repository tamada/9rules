package com.github.ninerules.parameters;

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
public class SourceLength extends IntegerParameter{
    public static SourceLength STRICT_LEVEL = new SourceLength(50);
    public static SourceLength GENERAL_LEVEL = new SourceLength(70);
    public static SourceLength ROUGH_LEVEL = new SourceLength(100);

    public SourceLength(int sourceLength){
        super(sourceLength);
    }

    public LineCount convertToLineCount(){
        return new LineCount(value);
    }
}
