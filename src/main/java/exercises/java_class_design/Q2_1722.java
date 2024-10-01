package exercises.java_class_design;

interface Classic {
    int version = 1;    // public, static, final
    public void read();
}

class MediaReader implements Classic {
    int version = 2;         // pkg-private, instance, non-final
    public void read() {
        // Insert code here
        //System.out.println(version); // this would print 2
        //System.out.println(this.version); // this would print 2
        //System.out.println((Classic)version); // compiler error, version is an int and Classic is an interface: Inconvertible types; cannot cast 'int' to 'exercises.java_class_design.Classic'
        //System.out.println(super.version); // compiler error - Cannot resolve symbol 'version' - no 'version' in Object
        //System.out.println(this.Classic.version); // compiler error - Cannot resolve symbol 'Classic'
        //System.out.println(MediaReader.version); // compiler error - Non-static field 'version' cannot be referenced from a static context
        System.out.println(((Classic)this).version); // correct one
        System.out.println(Classic.version);   // also correct - another way
    }
}

public class Q2_1722 {
    public static void main(String[] args) {
        MediaReader mr = new MediaReader();
        mr.read();
        // what can be inserted in the code above so that it will print 1?
    }
}
