[![Build Status](https://travis-ci.org/tamada/9rules.svg?branch=master)](https://travis-ci.org/tamada/9rules)
[![Coverage Status](https://coveralls.io/repos/github/tamada/9rules/badge.svg?branch=master)](https://coveralls.io/github/tamada/9rules?branch=master)
[![codecov](https://codecov.io/gh/tamada/9rules/branch/master/graph/badge.svg)](https://codecov.io/gh/tamada/9rules)
[![codebeat badge](https://codebeat.co/badges/7338b3d9-520f-429b-ba55-16aec78615d1)](https://codebeat.co/projects/github-com-tamada-9rules)
[![Gitter](https://badges.gitter.im/9rules/Lobby.svg)](https://gitter.im/9rules/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg?style=flat)](https://github.com/tamada/9rules/blob/master/LICENSE)

# 9rules

This tool validates the following nine rules for the object oriented programming exercise in Java language.

## Description

A book titled '[The ThoughtWorks Anthology: Essays on Software Technology and Innovation](https://pragprog.com/book/twa/thoughtworks-anthology)' are published.
Chapter 6 in the book introduces object calisthenics for better software design.
The rules shown in the book are as follows.

1. Use 1 level of indentation per method (`DONE`),
2. Do not use the `else` keyword (`DONE`),
3. Wrap all primitives and strings (`DONE`),
4. Use only 1 dot per line (`DONE`),
5. Do not abbreviate (`Not support yet`),
6. Keep all entities small,
     * 50 lines in a source file (`DONE`),
     * 3 lines in a method (`DONE`), and
     * 10 classses in a package (`Not support yet`).
7. Do not use any classes with more than 2 instance variables (`DONE`),
8. Use first-class collections (`DONE`), and
9. Do not use any getters/setters/properties (`DONE`)

Unfortunately, to confirm obeying the rules is by a human eye.
Therefore, this tool was developed to validate the rules automatically by analyzing given Java source codes.
By the way, this tool is programed to obey above rules.

## Demo

The following demo shows validating Java source codes in top directory of 9rules project.

![Demo](https://github.com/tamada/9rules/raw/master/src/site/static/images/demo.gif)

The demo was executed the following commands.

```sh
$ tree src/test/resources/hello/src/main/java/
// The result of tree command was printed.
$ java -jar target/9rules-1.0-SNAPSHOT.jar src/test/resources/hello/src/main/java/
// ... skip
src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java
line: 10, setter method found.
line: 14, getter method found.
line: 18, method is too long (over 3 lines).
// ... skip
$ cat -n src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java
// Print source code.
```

At the second command, the demo performed 9rules to validate given source directory.
One of the results was shown in above list, 9rules reports that the following violations were found.
You can find the violations to see the actual source code.

* setter method found in line 10,
* getter method found in line 14,
* the method declared in line 18 was too long, it over 3 lines.

## Usage

* To run the program of the project, use `-jar` option of the `java` command.
    * `$ java -jar target/9rules-1.X.jar <TARGET SOURCE DIR...>`

`<TARGET SOURCE DIR...>` accepts several directories and several source files.
The help message is as follows.

```sh
java -jar 9rules.jar [OPTIONS] <ARGUMENTS...>

OPTIONS:
    --strict:  Strictly level check (Default).
    --general: General level check.
    --rough:   Rough level check.
    --help:    Print this message and exit.

ARGUMENTS:
    Directories include Java source files, and Java source files.
```

If it is hard to obey the rules shown in above, we can lower the validating level by specifying the option.

* The strict level (`--strict` option) is just rules shown in above.
* The general level (`--general` option) is lowered as follows.
    1. Use 1 level indentation per method (same as strict),
    2. Do not use the `else` keyword (same as strict),
    3. Wrap all primitives and strings (same as strict),
    4. Use only 2 dots per line (**relieved**),
    5. Do not use abbreviate (not support yet),
    6. Keep all entities small,
        * 70 lines in a source file (**relieved**),
        * 5 lines in a method (**relieved**), and
        * 20 classes in a package (Not support yet).
    7. Do not use any classes with more than 3 instance variables (**relieved**),
    8. Use first-class collections (same as strict), and
    9. Do not use any getters/setters/properties (same as strict).
* The rough level (`--rough` option) is lowered as follows.
    1. Use 2 level indentation per method (**relieved**),
    2. Do not use the `else` keyword (same as strict),
    3. Wrap all primitives and strings (same as strict),
    4. Use only 3 dots per line (**relieved**),
    5. Do not use abbreviate (not support yet),
    6. Keep all entities small,
        * 100 lines in a source file (**relieved**),
        * 10 lines in a method (**relieved**), and
        * 30 classes in a package (Not support yet).
    7. Do not use any classes with more than 4 instance variables (**relieved**),
    8. Use first-class collections (same as strict), and
    9. Do not use any getters/setters/properties (same as strict).

### Docker

#### Run 9rules on Docker container.

Container images for Docker of 9rules are:

* [`tamada/9rules`](https://hub.docker.com/r/tamada/9rules) 
    * `1.0.0-v2`, `latest`

To run the 9rules by the docker container:

```sh
$ docker run --rm -v "$PWD":/home/ninerules tamada/9rules <ARGUMENTS...> 
```

* `ARGUMENTS...`: the arguments for `9rules`.
* `--rm`: remove the container after running.
* `-v "$PWD":/home/ninerules`: share volume `$PWD` in host to `/home/ninerules` in the container.

#### Example

```sh
$ docker run --rm -v "$PWD":/opt/wd -w /opt/wd tamada/9rules-minimal:latest src/main/java
```

## Install

* Clone the project from GitHub.
* Change directory to cloned project.
* Run the following command to build the project.

* Commands
    * `$ git clone git@github.com:tamada/9rules.git`
    * `$ cd 9rules`
    * `$ mvn package`

### Requirements

* Platform
    * Java 8 (unconfirmed in Java 9 platform)
    * Maven 3.x
* Dependencies
    * JDT Core 3.10.0
* Dependencies (Unit Test)
    * JUnit 4.12
    * Hamcrest all 1.3

## How to Contribute

1. Fork it (http://github.com/tamada/9rules)
2. Create your feature branch (`git checkout -b my-new-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
5. Run test suite (`mvn package`)
5. Push to the branch (`git push origin my-new-feature`)
6. Create new pull request

## LICENSE

Apache License v 2.0

## Author

* Haruaki Tamada

## References

* [Nine rules](http://binstock.blogspot.jp/2008/04/perfecting-oos-small-classes-and-short.html)
* [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do)


# 9rules （in Japanese）

このツールは，オブジェクト指向プログラミングエクササイズで言われている9つのルールが遵守できているかを確認するツールです．


## Description

[ThoughtWorksアンソロジー ――アジャイルとオブジェクト指向によるソフトウェアイノベーション](https://www.oreilly.co.jp/books/9784873113890/) という本がオライリージャパンから出版されている．
その本の第５章にて，オブジェクト指向エクササイズという，ソフトウェア設計を改善する9つのルールが紹介されている．
そのルールは次の通りである．

1. 1つのメソッドにつき，インデントは一段階とすること．`DONE`
2. `else`句を使用しないこと．`DONE`
3. すべてのプリミティブ型と文字列型をラップすること．`DONE`
4. 1行につき，ドットは1つまでにすること．`DONE`
5. 名前を省略しないこと．`未実装`
6. すべてのエンティティを小さくすること．
    * 1クラスは50行まで．`DONE`
    * 1メソッド3行まで．`DONE`
    * 1つのパッケージには10ファイルまで．`未実装`
7. １つのクラスにつき，インスタンス変数は２つまでにすること．`DONE`
8. ファーストクラスコレクションを使用すること．`DONE`
9. Getter，Setter，プロパティを使用しないこと．`DONE`

残念ながら，これらのルールに従っているかを確認する方法は目視のみでした．
そのため，与えられた Java ソースコードが上記ルールに従っているかを自動的に検証するため，このツールを作成しました．
なお，このツール自体も，上記ルールに従うよう作成しています．

## デモ

以下のデモでは，サンプルソースコードが 9rules のルールに従っているかを確認しています．
この実行結果を得るには，9rules プロジェクトのトップディレクトリにて同じコマンドを入力してください．

![Demo](https://github.com/tamada/9rules/raw/master/src/site/static/images/demo.gif)

実行しているコマンドは次の通りです．

```sh
$ tree src/test/resources/hello/src/main/java/
// tree コマンドの結果が表示される．
$ java -jar target/9rules-1.0-SNAPSHOT.jar src/test/resources/hello/src/main/java/
// ... 途中省略
src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java
line: 10, setter method found.
line: 14, getter method found.
line: 18, method is too long (over 3 lines).
// ... 途中省略
$ cat -n src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java
// ソースコードの内容を確認している．
```

2つ目のコマンドが実際に実行しているところです．
実行結果の，`src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java` を見てみましょう．
ルールの違反が3つあると出力されています．これはつまり以下の内容が指摘されています．
指摘された違反が正しいかを実際にソースコードをみて確認してください．

* 10行目に setter メソッドが見つかった．
* 14行目に getter メソッドが見つかった．
* 18行目のメソッドが長い（3行を超えている）

## 利用方法

* プログラムを実行するには，`java`コマンドの `-jar`オプションに 9rules-1.X.jar を指定してください．
    * `$ java -jar target/9rules-1.X.jar <TARGET SOURCE DIR...>`

`<TARGET SOURCE DIR...>` には複数のディレクトリや，Javaのソースファイルを指定できます．
ヘルプメッセージは次の通りです．

```sh
java -jar 9rules.jar [OPTIONS] <ARGUMENTS...>

OPTIONS:
    --strict:  Strictly level check (Default).
    --general: General level check.
    --rough:   Rough level check.
    --help:    Print this message and exit.

ARGUMENTS:
    Directories include Java source files, and Java source files.
```

上記に示した9つのルールの遵守が難しい場合は，オプションの指定で，違反レベルを下げることも可能です．
If it is hard to obey the rules shown in above, we can lower the validating level by specifying the option.

* `--strict`オプション（デフォルト）では，上記に示したルールで検証します．
* `--general`オプションでは，次のルールで検証します．
    1. 1つのメソッドにつき，インデントは一段階とすること（`---strict`と同じ）．
    2. `else`句を使用しないこと（`---strict`と同じ）．
    3. すべてのプリミティブ型と文字列型をラップすること（`---strict`と同じ）．
    4. 1行につき，ドットは2つまでにすること（**緩和**）．
    5. 名前を省略しないこと．`未実装`
    6. すべてのエンティティを小さくすること．
        * 1クラスは70行まで（**緩和**）．
        * 1メソッド5行まで（**緩和**）．
        * 1つのパッケージには20ファイルまで．`未実装`
    7. １つのクラスにつき，インスタンス変数は3つまでにすること（**緩和**）．
    8. ファーストクラスコレクションを使用すること（`---strict`と同じ）．
    9. Getter，Setter，プロパティを使用しないこと（`---strict`と同じ）．
* The rough level is lowered as follows.
    1. 1つのメソッドにつき，インデントは2段階とすること（**緩和**）．
    2. `else`句を使用しないこと（`---strict`と同じ）．
    3. すべてのプリミティブ型と文字列型をラップすること（`---strict`と同じ）．
    4. 1行につき，ドットは3つまでにすること（**更に緩和**）．
    5. 名前を省略しないこと．`未実装`
    6. すべてのエンティティを小さくすること．
        * 1クラスは100行まで（**更に緩和**）．
        * 1メソッド10行まで（**更に緩和**）．
        * 1つのパッケージには30ファイルまで．`未実装`
    7. １つのクラスにつき，インスタンス変数は4つまでにすること（**更に緩和**）．
    8. ファーストクラスコレクションを使用すること（`---strict`と同じ）．
    9. Getter，Setter，プロパティを使用しないこと（`---strict`と同じ）．
    
## インストール

* GitHub からプロジェクトをクローンしてください．
* クローンしてできたディレクトリに移動してください．
* Mavenコマンドを実行し，プロジェクトをビルドしてください．

* 上記の手順は以下のコマンドを実行するのと同じです．
    * `$ git clone git@github.com:tamada/9rules.git`
    * `$ cd 9rules`
    * `$ mvn package`

### 必須ライブラリなど

* Platform
    * Java 8（Java 9は未確認です）
    * Maven 3.x
* Dependencies
    * JDT Core 3.10.0
* Dependencies (Unit Test)
    * JUnit 4.12
    * Hamcrest all 1.3

## 貢献するには

1. プロジェクトをフォークしてください．(https://github.com/tamada/9rules)
2. フィーチャーブランチを作成してください．(`git checkout -b my-new-feature`)
3. プロジェクトに変更を加え，コミットしてください．(`git commit -m 'Add some feature'`)
5. 全てのテストが成功することを確認しましょう．(`mvn package`)
5. ブランチを GitHub に push してください．(`git push origin my-new-feature`)
6. 新たにプルリクエストを発行してください．

## ライセンス

Apache License v 2.0

## 著者

* Haruaki Tamada

## 参考

* [Nine rules](http://binstock.blogspot.jp/2008/04/perfecting-oos-small-classes-and-short.html)
* [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do)
