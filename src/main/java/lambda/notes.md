# Interfaces

* In general, when you create an interface, you are defining a contract for ***what*** a class can do; without saying anything about ***how*** the class will do it.
* A Class "signs" the contract with the keyword ***implements***.
* When implementing an interface, you are agreeing to adhere (obey) to the contract defined in the interface.
* If a concrete (non-abstract) class in implementing an interface, the compiler will ensure that the class has implementation code for each ***abstract*** method in the interface.
* Whereas a class can extend from only 1 other class, a class can implement many interfaces.
    * class Dog extends Animal implements Movable, Lovable
    * a Dog "is-a" : Animal, Movable and Lovable
* As of Java 8, it is now possible to inherit **concrete** methods from interfaces. Interfaces can now contain two types of concrete methods: *static* and *default*.
    * Implementation classes are NOT required to implement an interface's *static* or *default* methods. 
    * The *default* interface methods are inheritable but the *static* interface methods are not.
* Interfaces themselves are implicitly ***abstract***
    * public abstract interface I {} == public interface I {}
    * top-level interfaces can have *public* or *package-private* access
* All interface methods are implicitly *public*.
* All interface methods are implicitly *abstract*. (Unless declared as *default* or *static*)
* All variables declared in an interface must be *public*, *static* and *final* i.e. interfaces can only declare constants (not instance variables).
* As with *abstract* classes you cannot *new* an interface type but they can be used as references:
  * Printable p = new Printer(); //Printable is an interface

# Functional Interfaces

* A functional interface is an interface that has **only one abstract method**
* This is known as the SAM (Single Abstract Method) rule.
  * *default* methods do not count.
  * *static* methods do not count.
  * methods inherited from *Object* do not count.

# Lambdas

* A lambda expression is just a block of code that helps in making your code more concise.
* A lambda expression only works with functional interfaces.
* **A lambda expression is an instance of a class that implements a functional interface**.
* Lambdas look a lot like methods and in some quarters are called "anonymous methods". However, it is an instance with everything but the method stripped away.
* A lot can be inferred (by the compiler) from the interface definition (which remember, has only one abstract method). The lambda expression is the instance that implements the interface that has been boiled down to the bare essentials.

# Method References

* Lambda expressions help in making your code more concise (fewer keystrokes).
* Method references, can, in certain situations, help in making your lambda expressions even more concise.
* If all your lambda expression does is call one method, then that is an opportunity to use a method reference.
* If a lambda parameter is simply passed to another method, then redundancy os specifying the variable twice can be removed.
* There are 4 different styles/types:
  * Bound - instance known at compile time
  * Unbound - instance provided at runtime
  * Static
  * Constructor
* With method references ***context*** is key!
  * the functional interface type being created is hugely important in determining the context.
* The compiler turns your method references into lambdas in the background.