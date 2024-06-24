package org.example.generics;

public class GenericMethods {
    public static <T> void genericMethod(T t) {
        MyGeneric<T> myGeneric = new MyGeneric<>(t);
        System.out.println(myGeneric.getInstance());
    }
    public static <T, U, V> void register(T t, U u, V v) {
        Register<T, U, V> register = new Register<>(t, u, v);
        System.out.println("Register: " + register.getName() + "; " + register.getName());
    }
    public static <T> MyGeneric<T> createGeneric(T t) {
        return new MyGeneric<>(t);
    }

    public static void main(String[] args) {
        genericMethod("SK");
        genericMethod(1.1);

        register(new Dog(), "Shep", 3);
        register(new Cat(), "Whiskers", 2);

        MyGeneric<Integer> myGenI = createGeneric(4);
        System.out.println(myGenI.getInstance());
    }
}
