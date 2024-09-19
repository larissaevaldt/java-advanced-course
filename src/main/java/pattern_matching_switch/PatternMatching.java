package pattern_matching_switch;

public class PatternMatching {

    public static void whatType(Object o) {
        switch(o) {
            case String s -> System.out.println("String");
            case Integer i -> System.out.println("Integer");
            case null -> System.out.println("Null");
            // if default was not here there would be an error:
            // 'switch' statement does not cover all possible input values
            default -> System.out.println("Not recognised");
        }
    }

    public static void infoOnType(Object o) {
        switch(o) {
            // Guarded patterns from JEP 406 are not available since Java 19 preview
//            case String s && s.startsWith("A") -> System.out.println("String beginning with A: " + s);
//            case Integer i && i.intValue() > 10 -> System.out.println("Integer > 10" + i);
            case null -> System.out.println("Null");
            default -> System.out.println("Not recognised");
        }
    }

    public static void main(String[] args) {
        whatType("ABC");  // String
        whatType(122);    // Integer
        whatType(null);   // Null
        whatType(32.39);  // not recognised - Double
    }
}
