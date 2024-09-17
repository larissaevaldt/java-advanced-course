# Ensuring Data Integrity

## Accessibility
* limit access as much as possible - "principle of least privilege"
* instance variables/methods should be *private*

## Restrict extensibility
* prevent subclassing by marking the class as *final*.
* Immutable objects are objects that cannot be changed after creation.

## Immutable Objects
* They are secure objects and use the following guidelines:
  * Do not provide any "setter" methods.
  * Make all the fields "private" and "final".
  * Prevent subclassing (prevents overriding):
    * make the class *final*
    * make the constructor private and provide a *public static* factory method e.g. "createNewInstance"
  * Instance fields:
    * immutable types e.g. *String*
    * mutable types e.g. StringBuilder, do NOT share references i.e. use "defensive copying" and "advanced encapsulation"

## SQL Injection
* Where user input retrieves unexpected results.
* protection provided by *PreparedStatement* with *bind variables* (and not *Statement*).

## Command Injection
* Where operating system commands are used to retrieve unexpected results.
* protection provided via input validation using a whitelist (states what is allowed) and/or security policies (applying the principle of "least privilege").
  * applying together provides "defence in depth".

## Security Policies
* Can be used in addition to or instead of, a whitelist to prevent command injection attacks.
* If both are applied then this creates a layered approach called "defence in depth".
* Be careful that the policy obeys the principle of "least privilege".
* In other words, if your program only need to *read* a file, then it should only have *read* permission and not have *write* access.

## Denial of Service (DoS) Attacks
* A denial of service attack is where one or more requests are made with the purpose of disrupting services.
* This can be accomplished in a number of ways:
  * leaking resources - always use try-with-resources to ensure you do not leak resources (by not closing them). If an attacker notices you are leaking resources they can continuosly call that method until eventually you run out of memory.
  * working with extremely large files - check file size because you could also run out of memory processing the file.
  * inclusion attacks - where a file contains several other files e.g. "zip bomb"
  * could happen with an array as well, so check array size.

## Guidelines for Confidential Information
* Confidential information includes passwords and personal details such as address, date of birth, salary and account balance.
* Obviously, sensitive data should never be output on the screen, logged or end up in an exception stack trace.
* Data in memory must be protected also because if your program crashes then a dump file may be created:
  * use char[] instead of *String's*. Ex: the system console uses char[] as the return for the readPassword() method. If it was a String it could be kept in the String pool where it could exist in memory long after the code that uses it has run 
  * set confidential object references to null as soon as you are done with it - makes it immediately eligible for garbage collection. The whole principle here is you should have confidential data in memory for as short a time as possible.
  
