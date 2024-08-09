package org.example.lambda.assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class AssignmentTwo {

    public static void main(String[] args) {
        staticMR();
        boundMR();
        unboundMR();
        constructorMR();
    }

    public static void staticMR() {
        // a. in staticMR(), declare a List of integers with 1, 2, 7, 4, and 5 as values.
        List<Integer> numbers = Arrays.asList(1, 2, 7, 4, 5);
        System.out.println("Before sorting: " + numbers);
        // b. using a Consumer typed for List<Integer> and the Collections.sort static
        //method, code a lambda that sorts the list passed in.
        Consumer<List<Integer>> sorter = list -> Collections.sort(list);
        // c. invoke the lambda.
        sorter.accept(numbers);

        // d. prove that the sort worked.
        System.out.println("After sorting: " + numbers);

        //e. re-initialise the list (so it is unsorted again).
        numbers = Arrays.asList(1, 2, 7, 4, 5);
        System.out.println("After re-initialise: " + numbers);

        // f. code the method reference version.
        // i. sort() is overloaded : sort(List) and sort(List, Comparator)
        // ii. how does Java know which version to call?
        // because Consumer<List<Integer>> has a method accept(List<Integer> list) which takes a single argument of type List<Integer>.
        // when we use Collections::sort in the context of Consumer<List<Integer>>,
        // Java knows to call the sort(List<T> list) version because it is the only one that matches
        // the functional interface's method signature, which takes a single argument.
        Consumer<List<Integer>> sorterMr = Collections::sort;
        // g. invoke the method reference version.
        sorterMr.accept(numbers);
        // h. prove that the sort worked.
        System.out.println("After sorting: " + numbers);
    }

    public static void boundMR() {
        // a. in boundMR(), declare a String variable called name and initialise it to “Mr. Joe Bloggs”.
        String name = "Mr. Joe Bloggs";
        // b. using a Predicate typed for String, code a lambda that checks to see if name starts with the prefix passed in.
        Predicate<String> beginsWith = prefix -> name.startsWith(prefix);
        // c. invoke the lambda passing in “Mr.” which should return true.
        System.out.println(beginsWith.test("Mr."));
        // d. invoke the lambda passing in “Ms.” which should return false.
        System.out.println(beginsWith.test("Mrs."));
        // e. code the method reference version.
        Predicate<String> beginsWithMR = name::startsWith;
        // f. repeat c and d above except using the method reference version.
        // invoke the lambda passing in “Mr.” which should return true.
        System.out.println(beginsWithMR.test("Mr."));
        // invoke the lambda passing in “Ms.” which should return false.
        System.out.println(beginsWithMR.test("Mrs."));
    }

    public static void unboundMR() {
        // a. in unboundMR(), code a Predicate lambda typed for String that checks to see if
        // the string passed in is empty.
        Predicate<String> isEmpty = s -> s.isEmpty();
        // b. invoke the lambda passing in “” (returns true).
        System.out.println(isEmpty.test(""));
        // c. invoke the lambda passing in “xyz” (returns false)
        System.out.println(isEmpty.test("xyz"));
        // d. code the method reference version of the lambda from (a).
        Predicate<String> isEmptyMR = String::isEmpty;
        // e. repeat b and c above except using the method reference version.
        // invoke the lambda passing in “” (returns true).
        System.out.println(isEmptyMR.test(""));
        // invoke the lambda passing in “xyz” (returns false)
        System.out.println(isEmptyMR.test("xyz"));
        // f. code a BiPredicate lambda typed for String and String:
        // i. the lambda takes in two parameters (hence “Bi”)
        // ii. check if the first parameter starts with the second parameter
        BiPredicate<String, String> biPredicate = (s1, s2) -> s1.startsWith(s2);
        // iii. invoke the lambda twice:
        //1. passing in “Mr. Joe Bloggs” and “Mr.” (returns true)
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Mr."));
        //2. passing in “Mr. Joe Bloggs” and “Ms.” (returns false)
        System.out.println(biPredicate.test("Mr. Joe Bloggs", "Ms."));
        //g. code the method reference version of the lambda from (f).
        BiPredicate<String, String> biPredicateMR = String::startsWith;
        // h. test it as per above in (f.iii)
        //1. passing in “Mr. Joe Bloggs” and “Mr.” (returns true)
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Mr."));
        //2. passing in “Mr. Joe Bloggs” and “Ms.” (returns false)
        System.out.println(biPredicateMR.test("Mr. Joe Bloggs", "Ms."));
    }

    public static void constructorMR() {
        // a. in constructorMR(), code a Supplier typed for List<String> that returns a new ArrayList.
        Supplier<List<String>> supplier = () -> new ArrayList<>();
        // b. invoke the lambda to create a new List<String> named list.
        List<String> list = supplier.get();
        // c. add “Lambda” to the list.
        list.add("Lambda");
        // d. output the list to show it worked.
        System.out.println(list);
        // e. code the method reference version of the lambda:
        Supplier<List<String>> supplierMR = ArrayList::new;
        // i. re-initialise list by invoking the method reference version.
        list = supplierMR.get();
        // ii. add “Method Reference” to the list.
        list.add("Method Reference");
        // iii. output the list to show it worked.
        System.out.println(list);

        // f. next, we want to use the overloaded ArrayList constructor passing in 10 as the initial capacity (note: the default
        //    constructor assumes a capacity of 10).
        // i. thus, we need to pass IN something and get back OUT something:
        //    1. IN: 10 OUT: ArrayList
        //    ii. we need a Function typed for Integer and List<String> for this.
        //    iii. code the lambda.
        Function<Integer, List<String>> fn = i -> new ArrayList<>(i);
        //    iv. re-initialise the list by invoking the lambda passing in 10 as the capacity.
        list = fn.apply(10);
        //    v. add “Lambda” to the list.
        list.add("Lambda");
        //    vi. output the list to show it worked.
        System.out.println(list);

        // g. code the method reference version.
        //    i. note that the method reference version is the exact same as above in e!!
        //    ii. this is where context is all important:
        //        1. the first method reference was for a Supplier and Supplier’s functional method is T get() and thus, Java knew
        //        to look for the ArrayList constructor that takes in NO argument
        //
        //        2. the first method reference was for a Function and Function’s functional method is R apply(T t) and thus, Java knew
        //        to look for the ArrayList constructor that takes in ONE argument.
        Function<Integer, List<String>> fnMR = ArrayList::new;
        list = fnMR.apply(10);
        list.add("Method Reference");
        System.out.println(list);
    }
}
