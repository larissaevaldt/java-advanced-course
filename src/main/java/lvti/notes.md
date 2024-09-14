# Local Variable Type Inference (LVTI)
* Java is a strongly-typed language i.e. you must specify the type of a variable when introducing it.
* However, since java 10, we have the option, in certain scenarios, of using the keyword *var* instead of the type. In these situation, the compiler *infers* the type e.g. *var x = "abc"* // x is a String
* Remember that the variable must  be **local** and must be initialised on the line where they are created.
* The value of var can change but the type cannot.
  * var d = 123.3;
  * d = 78.3; //ok
  * d = "sss"; //not allowed
* var cannot be simply initialized to null; cast the null to a type first.
  * var j = null; // not allowed
  * var j = (String) null; //ok
* var not allowed in multi-variable declarations.
  * int a=3, b=4; //ok
  * var c=3, d=4; //not allowed
* var is a reserved name but not a reserved word
  * var var = 8; //ok
  * identifiers can be called var but not types names e.g. classes, interfaces, enums.

### Where they can be used: 
1. Constructors
2. Methods
3. Init blocks

### Where they CANNOT be used: 
1. Constructor/Method parameters
2. Static/Class variables 
3. There is one exception: Lambdas where the parameter type can be inferred

