[![Build Status](https://travis-ci.org/tamada/9rules.svg?branch=master)](https://travis-ci.org/tamada/9rules)
[![Coverage Status](https://coveralls.io/repos/github/tamada/9rules/badge.svg?branch=master)](https://coveralls.io/github/tamada/9rules?branch=master)
[![Gitter](https://badges.gitter.im/9rules/Lobby.svg)](https://gitter.im/9rules/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

# 9rules

以下の９つのルールが守られているかを確認するためのツール．

1. 1つのメソッドにつき，インデントは一段階とすること．
2. else句を使用しないこと．DONE
3. すべてのプリイティブ型と文字列型をラップすること．DONE
4. １行につき，ドットは１つまでにすること．
5. 名前を省略しないこと．
6. すべてのエンティティを小さくすること．
    * 1クラスは50行まで．DONE
    * 1メソッド3行まで．DONE
    * 1つのパッケージには10ファイルまで．
7. １つのクラスにつき，インスタンス変数は２つまでにすること．DONE
8. ファーストクラスコレクションを使用すること．DONE
9. Getter，Setter，プロパティを使用しないこと．DONE

1. Use One Level of Indentation per Method
2. Don’t Use the ```else``` Keyword
3. Wrap All Primitives and Strings
4. Use Only One Dot per Line
5. Don’t Abbreviate
6. Keep All Entities Small
7. Don’t Use Any Classes with More Than Two Instance Variables
8. Use First-Class Collections
9. Don’t Use Any Getters/Setters/Properties

See also [Nine rules](http://binstock.blogspot.jp/2008/04/perfecting-oos-small-classes-and-short.html), [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do)