---
title: Usage
---

## Usage of 9rules

9rules is an executable jar, therefore, simply type ```java -jar 9rules-VERSION.jar```,
and specified java source codes and source code directory.

Then, the tool checks each java source code and reports the results of the check.

## Example

We assume that the project was cloned and compiled with [Maven 3](http://maven.apache.org/).

### An simple use case

```sh
$ ls
LICENSE    README.md  pom.xml    src/       target/
$ java -jar target/9rules-1.0.0.jar  src/test/resources/hello/src/main/java/
src/test/resources/hello/src/main/java/sample/hello/GodObject.java
line: 53, else statement found.
line: 13,14,15,16, field count is more than 2
line: 13,14,15,16, not first class collection.
line: 43, Indentation level is too much (more than 1 indent level)
line: 28, Many dots per line (more than 1 dots)
line: 29, Many dots per line (more than 1 dots)
line: 13,14,15,16, no primitives.
line: 43, method is too long (over 3 lines).
line: 65, source code is too long (over 50 lines).
src/test/resources/hello/src/main/java/sample/hello/GodObjectButNotTarget.java
src/test/resources/hello/src/main/java/sample/hello/HelloWorld.java
line: 10, setter method found
line: 14, getter method found
line: 18, method is too long (over 3 lines).
src/test/resources/hello/src/main/java/sample/hello/Launcher.java
line: 10, method is too long (over 3 lines).
```

### Java source codes as input

Also, the tool accepts source code as input.

```
$ ls
LICENSE    README.md  pom.xml    src/       target/
$ java -jar target/9rules-1.0.0.jar  src/test/resources/hello/src/test/resources/hello/src/main/java/sample/hello/Launcher.java
src/test/resources/hello/src/main/java/sample/hello/Launcher.java
line: 10, method is too long (over 3 lines).
```

## Help

```sh
java -jar 9rules.jar [OPTIONS] <TARGETS...>

OPTIONS:
    --strict:  Strictly level check (Default).
    --general: General level check.
    --rough:   Rough level check.
    --help:    Print this message and exit.

TARGETS:
    Directories include Java source files, and Java source files.
```

### Checking Level

The check of default rules are quite hard to satisfy.
Therefore, the tool introduce three checking levels.
You can specify checking level in the command line option.

1. Strict level (```---strict``` option. default)
    * This level checks source codes strictly.
        * Rule # 1: Use 1 level of indentation per method.
        * Rule # 4: Use only 1 dot per line.
        * Rule # 6-1: The length of each method is less than 3 lines.
        * Rule # 6-2: The length of each class is less than 50 lines.
        * Rule # 7: Do not use any classes with more than 2 instance variables.
2. General level (```---general``` option)
    * This level checks source codes generally.
        * Rule # 1: Use 1 level of indentation per method.
        * Rule # 4: Use only 2 dot per line.
        * Rule # 6-1: The length of each method is less than 5 lines.
        * Rule # 6-2: The length of each class is less than 70 lines.
        * Rule # 7: Do not use any classes with more than 3 instance variables.
3. Rough level (```---rough``` option)
    * This level checks source codes roughly.
        * Rule # 1: Use 2 level of indentation per method.
        * Rule # 4: Use only 3 dot per line.
        * Rule # 6-1: The length of each method is less than 10 lines.
        * Rule # 6-2: The length of each class is less than 100 lines.
        * Rule # 7: Do not use any classes with more than 4 instance variables.

### Ignoring Targets

There is a case of classes could not satisfy the rules because of a certain frameworks' restriction.
When the tool evaluate Java source codes, the tool would ignore such classes by the annotation.

If the annotation ```@IgnoreRules``` was specified on class definition,
the class will not be checked by the tool.

In this version of ```@IgnoreRules``` annotation,
the tool ignore all rules by specifying the annotation, and checks all rules without the annotation.
In the future version, the tool will arrange the annotations for ignoring each rule.


## Demo

![demo](/9rules/images/demo.gif)

## Docker

The docker container images are provided at DockerHub as the following repository.

* [tamada/9rules](https://hub.docker.com/r/tamada/9rules) (tamada/9rules)
    * `1.0.0-v2`, `latest`

To run the 9rules by docker.

```sh
$ docker run --rm -v $PWD:/home/ninerules tamada/9rules <ARGUMENTS...> 
```

* `ARGUMENTS...`: the arguments for `9rules`.
* `--rm`: remove the container after running.
* `-v "$PWD":/home/ninerules`: share volume `$PWD` in host OS to `/home/ninerules` in the container OS.
    * Note that `$PWD` must be the absolute path.

### Example

```sh
$ docker run --rm -v $PWD:/home/ninerules tamada/9rules src/main/java
```

