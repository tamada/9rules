# 9 rules

## Rules

This tool checks the 9 rules of small object programming for Java source codes.
Originally, 9 rules were shown in [The ThoughtWorks Anthology](http://shop.oreilly.com/product/9781934356142.do).
The rules are as follows.

1. Use one level of indentation per method.
2. Do not use the ```else``` keyword.
3. Wrap all primitives and ```String```s.
4. Use only one dot per line.
5. Do not abbreviate.
6. Keep all entities small.
    * The length of each method is less than 3 lines.
    * The length of each class is less than 50 lines.
7. Do not use any classes with more than two instance variables.
8. Use first-class collections.
9. Do not use any getters/setters/properties.

## Implementation

The tool can check above rules except rule no 5.
To check rule no 5 must be refer the dictionaries.
Of course, a library like [WordNet](https://wordnet.princeton.edu) enables to check rule no 5.
However, the tool does not implement the rule yet.

