# Records
* Records are a special type of class that help avoid boilerplate code. They are considered "data carriers".
* Records are immutable and are *final* by default.
* You cannot extend your custom record because records already (implicitly) extend from the *Record* class. This is similar to enums (which implicitly extend from *Enum*).
* Records can have both *static* fields and *static* methods.
* Records can have instance methods.
* Records cannot have instance fields. All the instance fields are listed as "components" in the record declaration.
* Records can implement interfaces.
* Records are specified using a record declaration where you specify the "components" of the record
  * public record CarRecord(String regNumber, String owner) {}
* Implicitly generated are:
  * canonical constructor
  * *toString()* - the string representation of all the record class's components, with their names.
  * *equals()* and *hashCode()* - which specify that two record classes are equal if they are of the same type and contain equal component values.
  * *public* accessor methods with the same name as the components.
* You can override all the default implementations. This includes the canonical constructor (you may want to do this if you want to validate the data)
* Compact constructor is a concise variation of the canonical constructor and is specific to records.