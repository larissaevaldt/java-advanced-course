package annotations;

enum Device { LAPTOP, PHONE }

// 1. Define the annotation
// Can be public or package-private
@interface Human {} // marker annotation (no elements)

@interface OnWeb {
    // The elements - abstract and public by default
    // can't be protected, private or final
    int startTime() default 9;   // optional (due to the default value)
    int hoursPerDay();           // required element (no default value)

    // As in interfaces, values are public static final by default.
    int PEAK_TIME_START = 19;
    public static final int PEAK_TIME_END = 22;    //  public static final is not necessary

    // The element type must be a primitive type, a String, an enum, Class, another annotation or one dimension array.
//    Integer turnOff; // compiler error - wrapper types are nor allowed
    String name() default "LJ";
    Device consume() default Device.LAPTOP;
    Class humanOrBot() default Human.class;
    Human extraInfo() default @Human;
    String[] sites() default { "L", "J" };
}

// 2. Apply the annotation
@OnWeb(hoursPerDay = 6) @Human class Student {}

@OnWeb(hoursPerDay = 3, startTime = 18)
@Human
class Worker {}

public class CustomAnnotations {



}
