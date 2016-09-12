package com.github.ninerules;

/**
 * <ol>
 *   <li>1つのメソッドにつき，インデントは一段階とすること．</li>
 *   <li>else句を使用しないこと．</li>
 *   <li>すべてのプリイティブ型と文字列型をラップすること．</li>
 *   <li>１行につき，ドットは１つまでにすること．</li>
 *   <li>名前を省略しないこと．</li>
 *   <li>すべてのエンティティを小さくすること．
 *     <ul>
 *       <li>1クラスは50行まで．</li>
 *       <li>1メソッド3行まで．</li>
 *       <li>1つのパッケージには10ファイルまで．</li>
 *     </ul>
 *   </li>
 *   <li>１つのクラスにつき，インスタンス変数は２つまでにすること．</li>
 *   <li>ファーストクラスコレクションを使用すること．</li>
 *   <li>Getter，Setter，プロパティを使用しないこと．</li>
 * </ul>
 * 
 * @author Haruaki Tamada
 */
public enum StrictLevel {
    STRICT, GENERAL, ROUGH;
}
