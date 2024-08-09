package lambda;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class MethodRefsAndContext {
    public static void main(String[] args) {
        // No Person being passed in => Supplier
        Supplier<Integer> lambda1 = () -> Person.howMany();
        Supplier<Integer> mr1 = Person::howMany;
        System.out.println(lambda1.get());   // 0
        System.out.println(mr1.get());       // 0

        // One Person being passed in => Function
        Function<Person, Integer> lambda2 = person -> Person.howMany(person);
        Function<Person, Integer> mr2 = Person::howMany;
        System.out.println(lambda2.apply(new Person()));   // 1
        System.out.println(mr2.apply(new Person()));       // 1

        // Two Person's being passed in => BiFunction
        BiFunction<Person, Person, Integer> lambda3 = (p1, p2) -> Person.howMany(p1, p2);
        BiFunction<Person, Person, Integer> mr3 = Person::howMany;
        System.out.println(lambda3.apply(new Person(), new Person()));   // 2
        System.out.println(mr3.apply(new Person(), new Person()));       // 2

        // NOTE that all the method references have the same syntax
    }
}

class Person {
    public static Integer howMany(Person... people) {
        return people.length;
    }
 }
