package private_interface_methods;

// why 'private' interface methods?
//     1. Reduce code duplicates.
//     2. Improves encapsulation by hiding implementation details.
interface InefficientTennis { //lots of code duplication

    static void forehand() {
        System.out.println("Move into position");
        System.out.println("Hitting a forehand");
        System.out.println("Move back into ready position");
    }

    default void backhand() {
        System.out.println("Move into position");
        System.out.println("Hitting a backhand");
        System.out.println("Move back into ready position");
    }

    default void smash() {
        System.out.println("Move into position");
        System.out.println("Hitting a smash");
        System.out.println("Move back into ready position");
    }
}

/*
  Note: If 'hit' is simply 'private' and not 'private static' then 'forehand()' has an issue.
        This is the same as with classes (what I call de I.S rule) - instance to static is ok
        but not the other way around
 */
interface Tennis {
    private static void hit(String stroke) {
        System.out.println("Move into position");
        System.out.println("Hitting a " + stroke );
        System.out.println("Move back into ready position");
    }

    static void forehand() { hit("forehand"); }   // from a static method - ok
    default void backhand() { hit("backhand"); }  // from a default method - ok
    private void smash() { hit("smash"); }        // from another private method - ok

//    void volley() { hit("volley"); }    // abstract methods cannot have a body

}

class ProfessionalTennis implements Tennis {}

public class PrivateInterfaceMethods implements Tennis {

    public static void main(String[] args) {
        Tennis tennis = new ProfessionalTennis();
//        tennis.hit()     // compiler error - hit is private
//        tennis.smash();  // compiler error - hit is private
        tennis.backhand();
        Tennis.forehand();
//        x();     // compiler error - non-static method cannot be referenced from a static context
        new PrivateInterfaceMethods().x();
    }

    void x() {

    }

}