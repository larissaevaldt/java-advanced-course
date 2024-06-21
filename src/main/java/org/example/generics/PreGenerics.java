package org.example.generics;

import java.util.ArrayList;
import java.util.List;

public class PreGenerics {
    public static void main(String[] args) {
       // A raw collection can hold any type of Object (except a primitive)
        List myList = new ArrayList<>();    // can't enforce a type
        myList.add("Fred");                 // will hold Strings
        myList.add(new Dog());              // and Dogs
        myList.add(43);                     // and Integers (autoboxing)

        // As everything is treated as an Object, when you are getting something out of
        // the collection, all you had were Objects - therefore a cast was required to
        // get your String back
        String s = (String) myList.get(0); // cast required else compile error
        // and as we could not guarantee that what was coming out
        // was really a String (as we were allowed to put anything in),
        // this cast could fail at runtime
        String s1 = (String) myList.get(1); //ClassCastException at runtime

        // Generics takes care of both ends (putting in and getting out)
        // by enforcing the type of your collections.
        // Note: generic syntax means putting the type in angle brackets
        // here we have a List generically typed to String
        List<String> myList2 = new ArrayList<>();    // can't enforce a type
        myList2.add("Fred");                 // will hold Strings
        //myList2.add(new Dog());              // compile error
        // Because what is going IN is guaranteed, what is coming OUT is
        // also guaranteed => no need for the cast
        String s2 = myList2.get(0); // cast no longer required
    }

}
