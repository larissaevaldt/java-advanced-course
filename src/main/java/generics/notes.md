### Generics
* Generics were introduced in Java 1.5
* Arrays in Java have always been type-safe - an array declared as type String (String []) cannot accept Integer (or int), Dogs or anything other than String.
* Prior to generics, where the collections are known as "raw" collections, the Object class is used for storing elements in containers. This however, leads to type safety issues which cannot be detected by the compiler and only manifests themselves at runtime.
* A container that stores Object types has a critical weakness - type information is lost. This means that you must provide a cast when retrieving elements from the container. In addition and more importantly, the compiler is unable to determine if the cast is correct - a coding error. This results in a ClassCastException at runtime.
* **With generics, you can specify to the compiler that only elements of a certain type can be added to the container.**
* Generics will ensure that any attempt to add a type other than the particular type specified will be caught at compile time. This is known as "type-safety".
* In addition, generics enable you to write code for one type (for example T) that is applicable for all types (instead of having to write separate classes for each specific type).
* Generics offer "*generic implementation with type safety*".
* The generic type is the type in the angle brackets <>.

### Type Erasure
* Pre-generics, Java used *Object* types in collections.
* In order for generic code to be compatible with older pre-generic code, all of the type information is removed from the bytecode. This means that:
  * List<String> list = new ArrayList<String>(); is the same as:
  * List list = new ArrayList(); // legacy syntax, Object used
* Generics are strictly a compile-time protection feature

### Polymorphism and Generics
Polymorphism applies to the **base** type:
* **List**\<Integer> myList = new **ArrayList**\<Integer>()

Polymorphism does NOT to the **generic** type:
* List\<**Number**> myList = new ArrayList\<**Integer**>() 

````java
// The issue
List<Double> doubles = new ArrayList<Double>();
doubles.add(12.3);
List<Object> objects = doubles; // COMPILE ERROR
objects.add("This is a String");
````

### Wildcard Generic Type
* To solve the polymorphism issue for generics, we use the wildcard question mark symbol i.e. ?

| Type                 | Syntax         | Example                                               | Add items?    |
|----------------------|----------------|-------------------------------------------------------|---------------|
| Unbounded wildcard   | ?              | List<?> I = new LinkedList<Integer>();                | No - readonly |
| Upper bound wildcard | ? extends type | List<? extends Number> I = new LinkedList<Integer>(); | No - readonly |
| Lower bound wildcard | ? super type   | List<? super Number> I = new LinkedList<Object>();    | Yes           |

#### Bounded Wildcards
* Bounded wildcards are a way to limit (or "bound") the types that can be used.
* You can bound in both directions i.e. upward or downward.

#### extends
* This is the Downward syntax:
  * *someMethod(List<? extends Number> list)*
    * *list* is a method parameter that can handle lists of *Number*, *Integer*, *Double*, etc.
    * note: in this context, *extends* is used in a general sense to mean "extends" (as in classes) but **also "implements" (as in interfaces)**.
  * known as "*upper bounded wildcards*" - **restricts the unknown type to be a specific type or a subtype of that type**.
  * read-only

#### super
* This is the Upward syntax:
  * *someMethod(List<? super Integer> list)*
    * *list* is a method parameter that can handle lists of *Integer*, or any super type of *Integer*.
  * known as "*lower bounded wildcards*" - **restricts the unknown type to be a specific type or a super type of that type**.
  * safe to add to the collection

### Generic Classes
* We can add generics to our own types (classes and interfaces)
* The syntax is to declare a formal type parameter in angle brackets.
* This can be seen in the API:
  * *public interface List<**E**>* with:
    *   *boolean add(**E** e)*
* The "E" above is a placeholder for the type you pass in e.g. *List<String>* where *String* replaces E.

#### Naming Conventions
* Can be anything but the convention is to use single uppercase letters.
* E is for element; T is for a generic type
* K is a map key; V is a map value
* N is a number
* S, U, V are for multiple generic types

### Generic Interfaces
* Interfaces can declare formal type parameter also.

### Generic Methods
* Formal type parameters can also be used on methods.
* The generic marker (formal type parameter) is declared just before the return type. Note that the return type can also incorporate the generic marker (which can make the code tricky)