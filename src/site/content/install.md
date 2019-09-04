---
title: Install
---

## Homebrew

Install 9rules via [Homebrew](https://brew.sh), simply run:

```sh
$ brew tap tamada/brew
$ brew install ninerules
```

`9rules` command (wrapper for shell script) will be installed.

## Install yourself

```sh
$ git clone https://github.com/tamada/9rules.git
$ cd 9rules
$ mvn package
```

## Requirements

* Runtime
    * Java 8 or later.
* Development
    * Maven 3.x
* Dependencies
    * JDT Core 3.10.0
* Dependencies (the Unit Tests)
    * JUnit 4.12
    * Hamcreset all 1.3
