package exercises;

public class Operators {

    public static int operators() {

        int x1 = -4;
        int x2 = x1--; // after this line executes x1 = -5 and x2 = -4
        int x3 = ++x2; // after this line executes x2 = -3 and x3 = -3

        if (x2 > x3) {  // not true, x2 and x3 have the same value -3
            --x3;
        } else {
            x1++;   // this line executes and x1 becomes -4
        }

        return x1 + x2 + x3;  //  -4 + -3 + -3 = -10

    }

    public static void main(String[] args) {
        System.out.println(operators());
    }

}
