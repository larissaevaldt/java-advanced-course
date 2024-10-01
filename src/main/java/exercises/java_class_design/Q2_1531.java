package exercises.java_class_design;

import java.io.IOException;

class Base {
    int getStatusCode(Object obj) throws NullPointerException {
        return 1;
    }

    Object covr() {
        return null;
    }
}

class Sub extends Base {
    //@Override  - error: Method does not override method from its superclass
    int getStatusCode(String s) {     // an overload, not an override
        return 1;
    }

    // error: attempting to use incompatible return type - long instead of int
//    long getStatusCode(Object obj) {     // if the return type in the parent class of an overriden method is primitive,
//        return 1;                        // then the return type in the subtype overriding the method must match
//    }

    @Override
    int getStatusCode(Object obj) throws ClassCastException {     // ok because ClassCastException is also a runtime Exception
        return 1;
    }

//    @Override  // error: overridden method does not throw 'java.io.IOException'
//    int getStatusCode(Object obj) throws IOException {     // NOT allowed to add a checked Exception
//        return 1;
//    }

    @Override
    String covr() {         // co-variant return (sub-type of overriden method in parent)
        return "test";      // class allowed in sub-type overriding method
    }


}
public class Q2_1531 {
    public static void main(String[] args) {
        // NB: The crucial point here is the compiler looks at the reference type and
        // not the object type i.e. Base and not Sub.
        // Thus, the compiler checks the signature of the getStatusCode() in the Base and not Sub.
        // As a result, the following code does NOT have any exceptions logic at all.
        // That's ok, as the compiler does not check for RunTimeExceptions (hence they are called "unchecked")
        // Now, *IF* Sub's version of getStatusCode() was allowed to throw any (new/extra) *checked* exceptions,
        // there simply would be no code to handle it
        // That is why the overriding method CANNOT add new or extra checked exceptions to the method signature.
        Base b = new Sub();
        b.getStatusCode(new Object());
    }
}
