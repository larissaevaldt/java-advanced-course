package org.example.generics;

import java.util.ArrayList;
import java.util.List;

class Animal {}
class Dog extends Animal {}
class Terrier extends Dog {}
class Cat extends Animal {}
class Manx extends Cat {}

public class SuperAndExtends {

    public static void addAnimal(Animal[] animals) {
        animals[0] = new Dog();
        animals[1] = new Cat(); // ArrayStoreException generated - JVM knows the type
    }

    public static void addAnimal(List<Animal> animals) {
        animals.add(new Dog());
    }

    public static void main(String[] args) {
        // 1a. Polymorphic assignments
        // Generics came in Java 5. Type erasure required to support legacy code.
        // Thus, generics offer compile time protection.
        // Arrays have similar issues (polymorphic assignments) but the difference is
        // in how the compiler and JVM behave. Due to type erasure with generics,
        // the JVM does not know the types; with arrays the JVM does

        // First, let's look at arrays
        Dog[] dogs0 = {new Dog(), new Dog()};
        addAnimal(dogs0);

        // polymorphism ok for the base type; List -> ArrayList
        List<Cat> cats1 = new ArrayList<Cat>();
        // polymorphism not ok for the generic type; Animal -> Cat
//        List<Animal> animals = new ArrayList<Cat>();  // COMPILE ERROR
        List<Cat> cats2 = new ArrayList<Cat>();   // generic types on both sides
        List<Cat> cats3 = new ArrayList<>();      // or use type inference
        // As the JVM does not know the types (stripped out during type erasure)
        // the compiler has to step in.
        //addAnimal(cats2); // COMPILE ERROR

        // 1b. 'extends' - polymorphic assignments
        // Note: extends is read-only
        List<? extends Animal> animals1 = new ArrayList<Animal>();
//        animals1.add(new Animal()); // COMPILE ERROR - read-only
        List<? extends Animal> animals2 = new ArrayList<Dog>();
        List<? extends Animal> animals3 = new ArrayList<Terrier>();
        List<? extends Animal> animals4 = new ArrayList<Cat>();
        List<? extends Animal> animals5 = new ArrayList<Manx>();
//        List<? extends Animal> animals6 = new ArrayList<Object>();  // COMPILE ERROR - extends allows for Animal and anything extending it, anything downwards in the inheritance tree (subtypes not super types), so can't have it reference something above like Object

        // 1c. 'super' - polymorphic assignments
        List<? super Dog> dogs1 = new ArrayList<Dog>();
        dogs1.add(new Dog());  // now, can add to the List
        List<? super Dog> dogs2 = new ArrayList<Animal>();
        List<? super Dog> dogs3 = new ArrayList<Object>();
//        List<? super Dog> dogs4 = new ArrayList<Terrier>();  // COMPILE ERROR - super allows Dog and anything above it in the inheritance tree, so can't add Terrier as it's below Dog

        // 2. declarations for 'extends' and 'super' examples
        List<Object> objects = new ArrayList<>(); objects.add(new Object());
        List<Animal> animals = new ArrayList<>(); animals.add(new Animal());
        List<Cat> cats = new ArrayList<>(); cats.add(new Cat());
        List<Manx> manxCats = new ArrayList<>(); manxCats.add(new Manx());
        List<Dog> dogs = new ArrayList<>(); dogs.add(new Dog());
        List<Terrier> terriers = new ArrayList<>(); terriers.add(new Terrier());

        // 3. extends
        //      ext(List<? extends Animal> list) => read-only
        ext(animals);    // Animal is-an Animal         - OK
        ext(cats);      // Cat is-an Animal             - OK
        ext(manxCats);  // Manx is-an Animal            - OK
        ext(dogs);      // Dog is-an Animal             - OK
        ext(terriers);  // Terrier is-an Animal         - OK
//        ext(objects);   // Object is-NOT an Animal      - not OK

        // 4. supper
        //      spr(List<? super Cat> list) => modifiable
        spr(cats);      // Cat is a Cat                 - OK
        spr(animals);   // Animal is a supertype Cat    - OK
        spr(objects);   // Object is a supertype Cat    - OK
//        spr(dogs);      // compile error: Dog is not a supertype of cat  -  not OK
//        spr(terriers);  // compile error: Terrier is not a supertype of cat  -  not OK
//        spr(manxCats);  // compile error: Manx is not a supertype of cat  -  not OK
    }

    public static void spr(List<? super Cat> list) { // the "lower-bound" is Cat
        // IN: Cat, Animal, Object.
        // The only objects that can safely be added are any type of Cat (including subtypes)
        // because the method could be getting in a list of Animals or Objects or Cats
        list.add(new Cat());   // Cat is-a Cat (Cat is-a Animal, Cat is-a Object)
        list.add(new Manx());  // Manx is-a Cat (Manx is-a Animal, Manx is-a Object)

//        list.add(new Dog());     // compiler error - Dog is not a Cat
//        list.add(new Animal());  // compiler error - Animal is not a Cat (Cat is an Animal)
//        list.add(new Object());  // compiler error - Object is not a Cat (Cat is an Object)

//        for (Cat cat : list) {        // compiler error when reading - 'list' passed in could be Animals or Objects
//            System.out.println(cat);
//        }
//
//        for (Animal a : list) {        // compiler error when reading - 'list' passed in could be Objects
//            System.out.println(a);
//        }

        for (Object o : list) {        // ok - the only thing we can safely say is that the 'list'
            System.out.println(o);     // coming in can all be treated as Objects
                                       // Cat is an Object, Animal is an Object, Object is an Object.
        }

    }

    public static void ext(List<? extends Animal> list) {  // "upper-bound" is Animal
        // 'extends' implies read-only
        // IN: List<Animal>, List<Cat>, List<Manx>, List<Dog>, List<Terrier>

        // If 'extends' allowed us to add to the 'list', then we could take in
        // a List of Cats and add a Dog to it - thereby breaking type safety.

//        list.add(new Cat());   // compiler error if we try to modify the list
//        list.add(new Dog());   // compiler error if we try to modify the list
//        list.add(new Animal());   // compiler error if we try to modify the list

//        for (Dog dog : list) {      // compiler errors reading - list could be a list of Cats
//            System.out.println(dog);
//        }

        // No compiler error reading once we treat them as Animal; whether this method receives
        // in a list of Animal, Cat, Manx, Dog, or Terrier; they are *all* Animal.
        for (Animal animal : list) {
            System.out.println(animal);
        }
    }
}
