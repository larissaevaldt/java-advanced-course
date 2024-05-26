package org.example.lambda.assignment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

interface Printable<T> {

    void print(T t);
}

interface Retrievable<T> {
    T retrieve();
}

interface Evaluate<T> {
    boolean isNegative(T t);
}

interface Functionable<T, R> {
    R applyThis(T t);
}

public class AssignmentOne {

    public static void main(String[] args) {
        AssignmentOne a1 = new AssignmentOne();
        a1.consumer();
        a1.supplier();
        a1.predicate();
        a1.function();

        // 5) invoke the getPeople() – store the result in a variable named listPeople.
        List<Person> listPeople = getPeople();
        // 6) invoke the sortAge() method passing down listPeople;
        sortAge(listPeople);
        // 7) invoke the sortName() method passing down listPeople
        sortName(listPeople);
        // 8) invoke the sortHeight() method passing down listPeople
        sortHeight(listPeople);
    }

    public void consumer() {
        // a) Using a lambda expression, implement the Printable interface (typed for String).
        // The relevant method just prints out the String argument it receives.
        // Invoke the relevant method, passing in "Printable lambda".
        Printable<String> lambdaI = str -> System.out.println(str);
        String s = "Printable lambda";
        lambdaI.print(s);

        // b) Using both a lambda expression and a method reference, implement 1a using a Consumer.
        Consumer<String> printCLambda = s1 -> System.out.println(s1);
        Consumer<String> printCMR = System.out::println;
        printCLambda.accept(s);
        printCMR.accept(s);
    }

    public void supplier() {
        // a) Using a lambda expression, implement the Retrievable interface (typed for Integer).
        // The relevant method just returns 77.
        // Invoke the relevant method
        Retrievable<Integer> lambdaI = () -> 77;
        int i = lambdaI.retrieve();
        System.out.println(i);

        // b) Using a lambda expression, implement 2a using a Supplier.
        Supplier<Integer> supplierLambda = () -> 77;
        int i2 = supplierLambda.get();
        System.out.println(i2);
    }

    public void predicate() {
        // a) Using a lambda expression, implement the Evaluate interface (typed for Integer).
        // The relevant method returns true if the argument passed is < 0, otherwise it returns false.
        // Invoke the relevant method twice – the first time pass in -1 and the second time pass in +1
        Evaluate<Integer> lambdaI = (Integer i) -> i < 0;
        System.out.println(lambdaI.isNegative(-1)); // true
        System.out.println(lambdaI.isNegative(1));  // false

        // b) Using a lambda expression, implement 3a using a Predicate.
        Predicate<Integer> predicateLambda = i -> i < 0;
        System.out.println(predicateLambda.test(-1)); // true
        System.out.println(predicateLambda.test(1));  // false

        // c) Invoke the check() method with the following Predicate lambda expressions:
        // we want to know if a number is even (true)
        Predicate<Integer> predicate1 = i -> i % 2 == 0;
        System.out.println(check(4, predicate1));      // true
        System.out.println(check(7, i -> i % 2 == 0)); // false

        // we want to know if a String begins with “Mr.”
        System.out.println(check("Mr. Joe Bloggs", s -> s.startsWith("Mr."))); // true
        System.out.println(check("Ms. Ann Bloggs", s -> s.startsWith("Mr."))); // false

        // we want to know if a person is an adult (age >= 18)
        Person p1 = new Person("Mike", 33, 1.8);
        Person p2 = new Person("Ann", 13, 1.4);
        Predicate<Person> predicate2 = p -> p.getAge() >= 18;
        System.out.println(check(p1, predicate2));            // true
        System.out.println(check(p2, p -> p.getAge() >= 18)); // false
    }


    // 3c) Declare a generically-typed check() method (not in UML).
    // The first parameter is generic and the second parameter is a Predicate, also generically typed.
    // The check() method returns true/false
    public static <T> boolean check(T t, Predicate<T> predicate) {
        return predicate.test(t);
    }

    public void function() {
        // a) Using a lambda expression, implement the Functionable interface - the input type is Integer and the return type is String.
        // The relevant method returns the number passed in appended to the String “Number is: ”.
        // Invoke the relevant method passing in 25.
        Functionable<Integer, String> lambdaI = i -> "Number is: " + i;
        System.out.println(lambdaI.applyThis(25));

        // b) Using a lambda expression, implement 4a using a Function.
        Function<Integer, String> functionLambda = i -> "Number is: " + i;
        System.out.println(functionLambda.apply(25));
    }

    private static List<Person> getPeople() {
        List<Person> result = new ArrayList<>();
        result.add(new Person("Mike", 33, 1.8));
        result.add(new Person("Mary", 25, 1.4));
        result.add(new Person("Alan", 34, 1.7));
        result.add(new Person("Zoe", 30, 1.5));
        return result;
    }

    private static void sortAge(List<Person> listPeople) {
        // a) Using the Iterable sort() method (note: List extends Iterable), and the Comparator.comparing() method, sort the Person objects in ascending age order
        listPeople.sort(
                Comparator.comparing(p -> p.getAge()));  // lambda syntax
                //Comparator.comparing(Person::getAge)); // method reference syntax

        // b) Output the sorted list using the Iterable forEach() method passing in a lambda expression.
        //listPeople.forEach(p -> System.out.println(p)); //lambda syntax

        // 9. Refactor 6b, 7b and 8b to use method references instead of lambda expressions.
        listPeople.forEach(System.out::println);
    }

    private static void sortName(List<Person> listPeople) {
        // a) As in 6a except sort the Person objects in ascending name order.
        listPeople.sort(
                Comparator.comparing(p -> p.getName()));  // lambda syntax
                //Comparator.comparing(Person::getName)); // method reference syntax

        // b) Output the sorted list using the Iterable forEach() method passing in a lambda expression
        //listPeople.forEach(p -> System.out.println(p)); // lambda syntax

        // 9. Refactor 6b, 7b and 8b to use method references instead of lambda expressions.
        listPeople.forEach(System.out::println);
    }

    private static void sortHeight(List<Person> listPeople) {
        // a) As in 6a except sort the Person objects in ascending height order.
        listPeople.sort(
                Comparator.comparing(p -> p.getHeight()));   // lambda syntax
                // Comparator.comparing(Person::getHeight)); // method reference syntax

        // b) Output the sorted list using the Iterable forEach() method passing in a lambda expression.
        //listPeople.forEach(p -> System.out.println(p));  // lambda syntax

        // 9. Refactor 6b, 7b and 8b to use method references instead of lambda expressions.
        listPeople.forEach(System.out::println);
    }
}


class Person {
    String name;
    Integer age;
    Double height;

    public Person(String name, Integer age, Double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                '}';
    }
}
