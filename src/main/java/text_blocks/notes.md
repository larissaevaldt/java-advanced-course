# Text Blocks
* A text block is a *String* object and as a result, shares the same properties as *String* objects (immutable and interned)
  * you can call *String* methods on a text block.
* A text block begins with three double-quote characters followed by newline i.e. """
  * text blocks cannot be put on one line
  * the text of a text block cannot follow the """
* In String literals, embedded quotes must be escaped. This is not the case with text blocks.
* Depending on where you place the closing delimiter (the three double quotes), determines whether you have a closing "\n"
* Spacing is determined by the closing delimiter position or first non-space character, whichever is encountered first.
* All spaces (known as incidental spaces) leading up to that position, are stripped from all lines in the text block.
* Nota that, the above algorithm works, if the closing delimiter is on a line of it's own.