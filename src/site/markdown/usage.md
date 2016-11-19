# Usage

## Usage of 9rules

9rules is an executable jar, therefore, simply type ```java -jar 9rules-1.X.jar```,
and specified java source codes and source code directory.

Then, the tool checks each java source code and reports the results of the check.

### Example

We assume that the project was cloned and compiled with [Maven 3](http://maven.apache.org/).

#### An simple use case

```sh
$ ls
LICENSE    README.md  pom.xml    src/       target/
$ java -jar target/9rules-1.0.jar  src/test/resources/hello/src/main/java/
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

#### An Java source code as input

Also, the tool can arrow an source code as input.


```
$ ls
LICENSE    README.md  pom.xml    src/       target/
$ java -jar target/9rules-1.0.jar  src/test/resources/hello/src/test/resources/hello/src/main/java/sample/hello/Launcher.java
src/test/resources/hello/src/main/java/sample/hello/Launcher.java
line: 10, method is too long (over 3 lines).
```

### Help

```sh
java -jar 9rules.jar [OPTIONS] <TARGETS...>

OPTIONS:
    --strict:  Strictly level check (Default).
    --general: General level check.
    --rough:   Rough level check.
    --help:    Print this message and exit.

TARGETS:
    Directories includes source files, or source files.
```


## Build

### GitHub

This project is hosted in [GitHub](http://github.com/tamada/9rules/).
The following instructions build the executable jar from source code.

1. At first, clone the project code from GitHub by typing ```git clone https://https://github.com/tamada/9rules.git```.
2. Then, change directory to cloned directories ```9rules```.
3. Next, type ```mvn package``` to download dependencies from the Internet, compile source codes, and package the executable jar.
4. Finally, executable jar named ```9rules-1.X.jar``` will be generated in ```target``` directory in the project directory.

### Downloads 


