package org.example.collections;

class Foo {
    private int fooValue;
    Foo (int val) {
        fooValue = val;
    }
    int getFooValue() {
        return fooValue;
    }

    @Override
    public boolean equals(Object o) {
        // && short-circuits if 'o' is not of the type Foo and therefore the downcast
        // will never generate a ClassCastException
        if ((o instanceof Foo) && (((Foo)o).getFooValue() == this.fooValue)) {
            return true;
        } else {
            return false;
        }
        // on one line:
//        return (o instanceof Foo) && (((Foo)o).getFooValue() == this.fooValue);
    }

    @Override
    // NB: The contract requires only that two equal objects have equal hashcodes.
    public int hashCode() {
        return fooValue * 17; // using the same instance variable as equals()
    }
    // The following implementation does NOT violate the contract as two
    // equal objects will return the same hashcode 100. It is legal and even correct,
    // but horribly inefficient as all objects (including unequal ones) land in the same bucket
    // This implementation does not improve the search time, which is what hashcodes are supposed to do:
//    public int hashCode() { return 100; }
}

public class EqualsTest {
    public static void main(String[] args) {
        Foo f1 = new Foo(2);
        Foo f2 = new Foo(2);
        Foo f3 = new Foo(3);
        System.out.println(f1.equals(f2)); // true - if Foo didn't have equals overridden it would be false
        System.out.println(f1.equals("SK")); // false (no ClassCastException)
        System.out.println(f1.hashCode()); // 34
        System.out.println(f2.hashCode()); // 34
        System.out.println(f3.hashCode()); // 51
    }
}
