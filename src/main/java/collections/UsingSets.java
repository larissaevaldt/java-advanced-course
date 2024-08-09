package collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class UsingSets {

    public static void main(String[] args) {
        factoryMethods();
        treeSet();
        hashSet();
        linkedHashSet();
    }

    public static void factoryMethods() {
        // unmodifiable sets returned
        Set<String> of = Set.of("a", "b", "c");
        Set<String> copy = Set.copyOf(of);

        // of.add("d");         // UnsupportedOperationException
        // copy.add("d");       // UnsupportedOperationException

        // of.remove("a");      // UnsupportedOperationException
    }

    public static void treeSet() {
        // SUU - Sets are Unique and Unordered by default (TreeSet however will give you sorted order)
        Set<String> names = new TreeSet<>();
        names.add("John");
        names.add("John");
        names.add("Helen");
        names.add("Anne");
        // No duplicates, elements are sorted alphabetically
        System.out.println(names);  // [Anne, Helen, John]

        Set<Integer> numbers = new TreeSet<>();
        numbers.add(23);
        numbers.add(Integer.valueOf("21"));
        numbers.add(Integer.valueOf("11"));
        numbers.add(99);
        // No duplicates, elements are sorted numerically
        System.out.println(numbers); // [11, 21, 23, 99]
    }

    public static void hashSet() {
        // HashSet
        Set<Contact> contactHS = new HashSet<>();
        contactHS.add(new Contact("zoe", 45));
        contactHS.add(new Contact("zoe", 45)); // "zoe" only added once to the set
        contactHS.add(new Contact("alice", 34));
        contactHS.add(new Contact("andrew", 35));
        contactHS.add(new Contact("brian", 36));
        contactHS.add(new Contact("carol", 37));
        /* Output: (order is completely at random, not sorted by name or age, insertion order is not maintained either)
            brian, 36
            andrew, 35
            carol, 37
            alice, 34
            zoe, 45
         */
        for (Contact contact : contactHS) {
            System.out.println(contact);
        }
    }

    public static void linkedHashSet() {
        // LinkedHashSet
        // API: This implementation differs from HashSet in that it maintains
        // a doubly-linked list running through all of its entries. This linked list
        // defines the iteration ordering, which is the order in which the elements were
        // inserted into the set (it maintains insertion-order).
        // This implementation spares its clients from the unspecified, generally
        // chaotic ordering provided by the HashSet, without incurring the increased cost
        // associated with TreeSet (TreeSet has to keep them sorted, it takes a little bit longer to store and retrieve)
        Set<Contact> contactLHS = new LinkedHashSet<>();
        contactLHS.add(new Contact("zoe", 45));
        contactLHS.add(new Contact("zoe", 45));  // "zoe" added only once (Set)
        contactLHS.add(new Contact("alice", 34));
        contactLHS.add(new Contact("andrew", 35));
        contactLHS.add(new Contact("brian", 36));
        contactLHS.add(new Contact("carol", 37));

        /* Output: (order is completely at random, not sorted by name or age, insertion order is not maintained either)
            zoe, 45
            alice, 34
            andrew, 35
            brian, 36
            carol, 37
         */
        for (Contact contact : contactLHS) {
            System.out.println(contact);
        }
    }
}
