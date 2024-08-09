package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class MethodReferenceTypes {

    public static void main(String[] args) {
        boundMethodReferences();
        unboundMethodReferences();
        staticMethodReferences();
        constructorMethodReferences();
    }

    public static void boundMethodReferences() {
        String name = "Mr. Joe Bloggs";

        // Supplier<T>
        //      T get()
        Supplier<String> lowerL = () -> name.toLowerCase();
        Supplier<String> lowerMR = name::toLowerCase;

        // no need to say which instance to call it on - the supplier is bound to name
        System.out.println(lowerL.get()); // mr. joe bloggs
        System.out.println(lowerMR.get()); // mr. joe bloggs

        // Predicate<T>
        //      boolean test(T t)
        Predicate<String> titleL = (title) -> name.startsWith(title);
        Predicate<String> titleMR = name::startsWith;

        // Even though startsWith is overloaded, there are two versions of this method:
        // boolean startsWith(String) and
        // boolean startsWith(String, int), because we are creating a Predicate which
        // has a functional method od test(T t), the startsWith(String) is used.
        // This is where "context" is important.
        System.out.println(titleL.test("Mr."));//true
        System.out.println(titleMR.test("Mrs."));//false
    }

    public static void unboundMethodReferences() {
        // Function<T, R>
        //      R apply(T)
        //          String apply(String)
        Function<String, String> upperL = s -> s.toUpperCase();
        Function<String, String> upperMR = String::toUpperCase;

        // the function is unbound, so you need to specify which instance to call it on
        System.out.println(upperL.apply("larissa")); // LARISSA
        System.out.println(upperMR.apply("larissa")); // LARISSA

        // BiFunction<T, U, R>
        //      R apply(T t, U u)
        //          String apply(String, String)
        BiFunction<String, String, String> concatL = (s1, s2) -> s1.concat(s2);
        BiFunction<String, String, String> concatMR = String::concat;
        System.out.println(concatL.apply("Larissa ", "Evaldt")); //Larissa Evaldt

        // 1st parameter is used for executing the instance method
        // "Larissa ".concat("Evaldt")
        System.out.println(concatMR.apply("Larissa ", "Evaldt")); //Larissa Evaldt

    }

    public static void staticMethodReferences() {
        // Static method references are considered UNBOUND also. An example static method
        // is Collections.sort(List)
        // Consumer<T>
        //     void accept(T t)
        // NB: Consumer takes one parameter => sort(List) is used, as opposed to sort(List, Comparator)
        Consumer<List<Integer>> sortL = list -> Collections.sort(list);
        Consumer<List<Integer>> sortMR = Collections::sort;

        List<Integer> listOfNumbers = Arrays.asList(2,1,5,4,9);
        sortL.accept(listOfNumbers);       // execution
        System.out.println(listOfNumbers); // [1, 2, 4, 5, 9]

        listOfNumbers = Arrays.asList(8,12,4,3,7);
        sortMR.accept(listOfNumbers);       // execution
        System.out.println(listOfNumbers); // [3, 4, 7, 8, 12]
    }

    public static void constructorMethodReferences() {
        // Supplier<T>
        //     T get()
        Supplier<StringBuilder> sbL = () -> new StringBuilder(); //lambda
        Supplier<StringBuilder> sbMR = StringBuilder::new;       //method reference

        // Function<T, R>
        //     R apply(T)
        //         List<String> apply(Integer)
        // ArrayList(int initialCapacity)
        Function<Integer, List<String>> alL = x -> new ArrayList(x);
        Function<Integer, List<String>> alMR = ArrayList::new;
        List<String> ls1 = alL.apply(10); // size 10
        ls1.add("21");
        System.out.println(ls1); // [21]
        List<String> ls2 = alMR.apply(5); // size 5
        ls2.add("88");
        System.out.println(ls2); // [88]
    }
}
