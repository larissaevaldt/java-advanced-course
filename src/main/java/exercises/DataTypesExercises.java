package exercises;

public class DataTypesExercises {

    public static void main(String[] args) {
        String str = "10";
        int iVal = 0;
        Double dVal = 0.0;
        try {
            iVal = Integer.parseInt(str, 2);  // iVal will be equal to 2 - "10" is treated as a binary numeber because of radix 2
            if ((dVal = Double.parseDouble(str)) == iVal) { //2
                System.out.println("Equal");
            }
        } catch (NumberFormatException e) {
            System.out.println("Exception in parsing");
        }
        System.out.println(iVal + " " + dVal);

        // retrieve "456" out of the string below:
        String str2 = "01234567";
        System.out.println(str2.substring(4, 7)); //substring begin index is inclusive, and end index is exclusive

//        Object t = new Integer(107); // compile error
//        int k = (Integer) t.intValue()/9; //Cannot resolve method 'intValue' in 'Object

    }
}
