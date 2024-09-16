package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

//@Target({ElementType.FIELD, ElementType.PARAMETER}) // with this uncommented, class, interface and constructor will have a compiler error
//@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.CONSTRUCTOR}) // with this uncommented, class and interface will have a compiler error
@Target({ElementType.TYPE_USE}) // clears all errors
@interface DataItem {}

@DataItem class X {}

@DataItem interface Y {}

class Z {
    @DataItem int a;
    @DataItem static int b;

    @DataItem Z() {}

    void m1(@DataItem int a) {}
}

@Target(ElementType.TYPE_USE)
@interface Wildcard {}

class X1 {
    @Wildcard int x;  // instance variable
    @Wildcard static int y;  // class variable

    void m1(@Wildcard int a) {  // method parameter

        @Wildcard int z = 0;   // local variable
        var x1 = new @Wildcard X1(); // new instance

        int n = (@Wildcard int) 23.9; // on a cast
    }

}

public class TargetExample {
    public static void main(String[] args) {

    }
}
