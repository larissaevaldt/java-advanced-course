# Modules
* Introduced in Java 9, provide an extra layer of encapsulation by enabling us to group related packages.
* From a high-level, modules enable us to specify well-defined boundaries/dependencies in our code base.
* As a developer, you can specify which packages are accessible outside the module and also the module dependencies (the other modules that the module itself depends upon).
* By default, all classes in the packages in a module are strongly encapsulated i.e. no other module can use these classes, even if they are *public* classes.
* Package names and module names live in separate namespaces i.e. you can have the same identifiers without any conflict (name clash)
* A module contains one or more packages plus a *module-info.java* file.
* When compiling a module, it is more convenient to use "module directories" as otherwise we could have to list all the .java source files (including module-info.java).
* If you use a module directory, then the name of the module directory must match the name of the module in the module-info.java file.
* Place the module-info.java file in the root of the source tree of the module you are describing.

## Why modules
* Improved access control:
  * in addition to *private*, package-private, *protected* and *public* now we have the ability to restrict packages to certain other packages.
* Improved large-scale structure of applications:
  * boundaries/dependencies in your code base.
  * hide implementation details.
  * improved decoupling.
* Reduced build sizes:
  * as Java itself is now modularised, developers can specify which of the Java API modules are required for their custom applications; this results in smaller application sizes (a consideration for memory-constrained devices).
* Earlier detection of missing code:
  * prior to modules, missing classes/JAR files might only be realised at runtime, when the class/JAR was being used for the first time.
  * as modules specify their dependencies - in a fully modular environment, the Java VM can check the whole dependency graph when it starts up; thus, any missing modules would be spotted much earlier.
  


