package org.example.collections;

import java.util.HashMap;
import java.util.Map;

class Contato {
    private int age;
    private String name;

    public Contato(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Contato) {       // same instance variables used in equals()!
            Contato otherContact = (Contato) o;
            return this.name.equals(otherContact.name) && this.age == otherContact.age;
        }
        return false;
    }

    @Override
    public int hashCode() {          // both instance variables 'age and 'name' are used
        int hash = 7;
        hash = 89 * hash + this.age;
        hash = 89 * hash + this.name.length(); // a weak algorithm - demo purposes
        return hash;
    }

    @Override
    public String toString() {
        return name + ", " + age;
    }
}
public class ContactTest {

    private static Map<Contato, String> map = new HashMap<>();

    public static void main(String[] args) {
        Contato john = new Contato(33, "john");
        Contato peter = new Contato(34, "peter");
        System.out.println("john.hashCode() is " + john.hashCode());    // 58388
        System.out.println("peter.hashCode() is " + peter.hashCode());  // 58478
        map.put(john, "Irish");
        map.put(peter, "American");
        System.out.println(map.get(john));  // Irish
        System.out.println(map.get(peter)); // American

        Contato mary = new Contato(21, "mary");
        System.out.println("mary.hashCode() is " + mary.hashCode());  // 57320
        map.put(mary, "engineer");
        otherScope();
        System.out.println("After otherScope(): " + map.get(mary)); // accountant

        testJane();
        // HashMaps do not maintain order
        System.out.println(map); // {john, 33=Irish, mary, 21=accountant, jane, 21=nurse, peter, 34=American}

    }

    public static void otherScope() {
        Contato anotherMary = new Contato(21, "mary"); // reconstruct "anotherMary"
        System.out.println("anotherMary.hashCode() is " + anotherMary.hashCode());  // 57320
        // Separate object used as a key to access the Map!
        System.out.println("In otherScope(): " + map.get(anotherMary)); // engineer

        // the next line overwrites mary with anotherMary as a key because:
        //    a) their hashcodes are the same
        //    b) they are both equal according to equals()
        map.put(anotherMary, "accountant");
    }

    public static void testJane() {
        // "jane" will result in the same hashcode as "mary" or "anotherMary"
        // because their ages are the same and "mary" and "jane" each have 4 letters.
        // Thus, the hashing algorithm will find the same bucket.
        // The equals() method now however finds a different key because we are searching
        // based on "jane" within the bucket anf not for "mary" as above.
        // As a result, when we do the "get", we get "nurse" and not "accountant"
        Contato jane = new Contato(21, "jane");
        System.out.println("jane.hashCode() is " + jane.hashCode());  // 57320
        map.put(jane, "nurse");
        System.out.println(map.get(jane)); // nurse
    }
}
