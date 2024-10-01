package exercises.java_class_design;

class PlaceHolder<K, V> {
    private K k;
    private V v;

    public PlaceHolder(K k, V v) {
        this.k = k;
        this.v = v;
    }

    public K getK() {
        return k;
    }

    public static <X> PlaceHolder<X, X> getDuplicateHolder(X x) {
        return new PlaceHolder<X, X>(x,x);
    }

    public static void main(String[] args) {
        PlaceHolder<String, String> ph1 = PlaceHolder.getDuplicateHolder("B");           //1

        // wrong - the . should have been before <String> like this: PlaceHolder.<String>getDuplicateHolder("b");
//        PlaceHolder<String, String> ph3 = PlaceHolder<String>.getDuplicateHolder("b");  // 2

        // this is invalid for the same reason as above
//        PlaceHolder<String, String> ph4 = PlaceHolder<>.getDuplicateHolder("b");        // 3

        // wrong cannot leave the <> empty on the left side of =
        // this should be written as PlaceHolder<String, String> ph5 = new PlaceHolder<>("a", "b");
//        PlaceHolder<> ph5 = new PlaceHolder<String, String>("a", "b");               // 4

        PlaceHolder<?, ?> ph6 = new PlaceHolder(10, 10);

    }
}
public class Q2_1825 {
}
