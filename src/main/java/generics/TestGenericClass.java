package generics;


class MyGeneric<T> {
    T instance;

    public MyGeneric(T instance) {
        this.instance = instance;
    }

    public T getInstance() {
        return instance;
    }
}

public class TestGenericClass {
    public static void main(String[] args) {
        // String on left-hand side maps to T and "SK" on right-hand side maps to 'instance'
        MyGeneric<String> g = new MyGeneric<>("SK");
        System.out.println(g.getInstance());
        // Integer on left-hand side maps to T and 1 on right-hand side maps to 'instance'
        MyGeneric<Integer> g2 = new MyGeneric<>(1);
        System.out.println(g2.getInstance());
    }
}
