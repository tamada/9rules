---
title: ":page_facing_up: Description"
---

## Rules

This tool checks the 9 rules of small object programming for the Java source codes.
Originally, 9 rules were shown in [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do).
The rules are as follows.

* Rule # 1. Use one level of indentation per method.
* Rule # 2. Do not use the ```else``` keyword.
* Rule # 3. Wrap all primitives and ```String```s.
* Rule # 4. Use only one dot per line.
    * Except the following items.
        * one line comments,
        * string literals,
        * ```this.```,
        * lines of ```import```, 
        * line of ```package```, 
        * first characters in the line,
        * variadic methods, and
        * ```System.out.print(ln|f)?```.
* Rule # 5. Do not abbreviate.
* Rule # 6. Keep all entities small.
    * The length of each method is less than 3 lines.
    * The length of each class is less than 50 lines.
    * The class files in a package are less than 10 files.
* Rule # 7. Do not use any classes with more than two instance variables.
* Rule # 8. Use first-class collections.
* Rule # 9. Do not use any getters/setters/properties.

## Implementation

The tool can check above rules except rule # 5.
To check rule # 5 must be refer the dictionaries.
Of course, a library like [WordNet](https://wordnet.princeton.edu) enables to check rule # 5.
However, the tool does not implement the rule yet.

