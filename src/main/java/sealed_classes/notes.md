# Sealed Classes
* Inheritance enables any class to inherit from any other class.
* Making a class *final* prevents any class from inheriting from that class i.e. the *final* class cannot become a super type at all.
* What if you wanted your class to be available for inheritance but only to certain classes?
* Sealed classes enable us to control the scope of inheritance by enabling us to specify a class subtypes.
* Also works with interfaces (we can define what classes *implement* that interface)