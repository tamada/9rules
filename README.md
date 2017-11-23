[![Build Status](https://travis-ci.org/tamada/9rules.svg?branch=master)](https://travis-ci.org/tamada/9rules)
[![Coverage Status](https://coveralls.io/repos/github/tamada/9rules/badge.svg?branch=master)](https://coveralls.io/github/tamada/9rules?branch=master)
[![codecov](https://codecov.io/gh/tamada/9rules/branch/master/graph/badge.svg)](https://codecov.io/gh/tamada/9rules)
[![codebeat badge](https://codebeat.co/badges/7338b3d9-520f-429b-ba55-16aec78615d1)](https://codebeat.co/projects/github-com-tamada-9rules)
[![Gitter](https://badges.gitter.im/9rules/Lobby.svg)](https://gitter.im/9rules/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![Quality Gate](https://sonarqube.com/api/badges/gate?key=com.github:9rules)](https://sonarqube.com/dashboard/index/com.github:9rules)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/9rules/blob/master/LICENSE)
[![badge](https://img.shields.io/badge/technical%20debt-visualizing-orange.svg)](http://rocat.naist.jp/heizo/9rules.html)

# 9rules

## Overview

This tool aims to confirm the nine rules of the object oriented programming exercise.
The nine rules are shown as follows.

1. Use One Level of Indentation per Method
2. Don’t Use the ```else``` Keyword
3. Wrap All Primitives and Strings
4. Use Only One Dot per Line
5. Don’t Abbreviate
6. Keep All Entities Small
7. Don’t Use Any Classes with More Than Two Instance Variables
8. Use First-Class Collections
9. Don’t Use Any Getters/Setters/Properties

This tool analyzes Java source codes to validate above rules.

このツールは，オブジェクト指向プログラミングエクセサイズで言われている9つのルールが遵守できているかを確認するツールです．
具体的なルールは次に示す通りです．

1. 1つのメソッドにつき，インデントは一段階とすること．```DONE```
2. ```else```句を使用しないこと．```DONE```
3. すべてのプリイティブ型と文字列型をラップすること．```DONE```
4. １行につき，ドットは１つまでにすること．```DONE```
5. 名前を省略しないこと．
6. すべてのエンティティを小さくすること．
    * 1クラスは50行まで．```DONE```
    * 1メソッド3行まで．```DONE```
    * 1つのパッケージには10ファイルまで．
7. １つのクラスにつき，インスタンス変数は２つまでにすること．```DONE```
8. ファーストクラスコレクションを使用すること．```DONE```
9. Getter，Setter，プロパティを使用しないこと．```DONE```

このツールは，ソースコードを解析し，上記の9つのルールを守っているかを確認します．

## Author

* Haruaki Tamada

## Requirements

* Platform
    * Java 9 (Module is not supported, yet)
    * Maven 3.x
* Dependencies
    * JDT Core 3.10.0
* Dependencies (Unit Test)
    * JUnit 4.12
    * Hamcrest all 1.3

## Usage

* To build the project, run the following command.
    * ```$ mvn package```
* To run the program of the project, use ```-jar``` option of the ```java``` command.
    * ```$ java -jar target/9rules-1.X.jar <TARGET SOURCE DIR>```

## References

* [Nine rules](http://binstock.blogspot.jp/2008/04/perfecting-oos-small-classes-and-short.html)
* [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do)

