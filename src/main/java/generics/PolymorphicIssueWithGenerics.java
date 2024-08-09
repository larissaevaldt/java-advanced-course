package generics;

import java.util.ArrayList;
import java.util.List;

public class PolymorphicIssueWithGenerics {

    public static void showList(List<Object> list) {
        for (Object o : list) {
            System.out.println(o);
        }
    }

    public static void main(String[] args) {
        // The issue
        List<Double> doubles = new ArrayList<Double>();
        doubles.add(12.3);
        //List<Object> objects = doubles; // COMPILE ERROR
        //objects.add("This is a String"); // otherwise would have doubles and strings in the same List

        // A different variation
        List<String> names = new ArrayList<String>();
        names.add("Sean");
        //showList(names);  // COMPILE ERROR - List<Object> list = new ArrayList<String>();
    }
}
