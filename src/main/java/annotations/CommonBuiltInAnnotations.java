package annotations;



@FunctionalInterface
interface Moveable {
    void move();
//    void run();   // compiler error - @FunctionalInterface annotation does not allow more than one abstract method
    boolean equals(Object o);  // Object methods are not counted
}

// on the background the compiler adds in extends Object
// class Person extends Object implements Moveable
class Person implements Moveable {
    private String name;
    public Person(String name) {
        this.name = name;
    }

    // @Override tells any developer that we are overriding a method from the parent
    @Override
    public String toString() {
        return name;
    }

    @Override
    public void move() {  // note: must be 'public'
        System.out.println("Moving");
    }
}

public class CommonBuiltInAnnotations {
    public static void main(String[] args) {
        Person lj = new Person("Larissa Justo");
        System.out.println(lj); // lj.toString() called in the background
        lj.move();
    }
}
