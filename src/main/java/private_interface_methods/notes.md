# Private Interface methods
* Introduced in Java 9, interfaces can now have *private* methods.
* These *private* methods can be both *static* and non-static.
* As they are *private*, they are accessible withing the interface only.
* As with classes, you cannot access a non-static interface method from a *static* method.
* 'private' and 'default' can not be used together because the default is intended to be used by types that implement the interface, whereas private means it's just private to the interface.

### Advantages
1. reduce code duplication
2. improve code encapsulation