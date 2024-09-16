package annotations;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Book {
    /**
     * How to consume the Book.
     * @deprecated Use readOnline() instead.
     */
    @Deprecated(since = "2.0", forRemoval = true)
    public static void print() {}
    public static void readOnly() {}
    public static Integer preview(List<String> pages) { return pages.size(); }
}
public class CommonBuiltInAnnotationsExtra {
    public static void main(String[] args) {
        CommonBuiltInAnnotationsExtra ann = new CommonBuiltInAnnotationsExtra();
        ann.testDeprecated();
        ann.testUnchecked();
    }

    @SuppressWarnings("deprecation")
    public void testDeprecated() {
        // prints a warning when compiling: print() in Book has been deprecated and marked for removal
        Book.print();
    }

    @SuppressWarnings("unchecked")   // ignore warnings relating to the use of 'raw types'
    public void testUnchecked() {
//        Book.preview(new ArrayList<>());
        // prints a warning when compiling: uses unchecked or unsafe operations.
        Book.preview(new ArrayList());  // this is a raw type - "new ArrayList<String>()" would be better
    }

    @SafeVarargs
    static int abuseVarargs(List<Integer>... list) {  // must have varargs and be private, static or final
        Object[] oa = list;
        oa[0] = Arrays.asList("uh-oh!");
        return list[0].get(0);  // ClassCastException : String to Integer
    }
}
