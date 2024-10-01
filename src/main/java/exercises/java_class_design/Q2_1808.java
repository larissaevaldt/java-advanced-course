package exercises.java_class_design;

class Book2 {
    String isbn;
    String title;

    public Book2(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    // INSERT CODE HERE
    // a - throws Exception is a problem - can't introduce checked Exceptions
    // 'equals(Object)' in 'exercises.java_class_design.Book2' clashes with 'equals(Object)' in 'java.lang.Object';
    // overridden method does not throw 'java.lang.Exception'
//    public boolean equals(Object o) throws Exception {
//        return true;
//    }

    // b - prints false - supposed to be taking in an Object, but we are taking a Book, it's a valid-overloaded method
    // when we do b1.equals(b2), because b1 and b2 are Object references, it will call the one in the parent
    // default behavior will be executed which is to compare the two references
    // If the parameter was Object o instead of Book o then it would print true
//    public boolean equals(Book o) {
//        return true;
//    }

    //c
    // prints false - same problem, we've got Book coming in
    // also, it's returning int instead of boolean which is what you're supposed to return if you're overriding the equals from Object
    // even if it did get called it would come back with a number instead of true
//    public int equals(Book2 b) {
//        return b.isbn.compareTo(this.isbn);
//    }

    // d - correct one
    // NullPointerException is a runtime Exception and the compiler is not interested in runtime Exceptions
    public boolean equals(Object o) throws NullPointerException {
        return true;
    }
}

public class Q2_1808 {
    public static void main(String[] args) {
        Object b1 = new Book2("1111", "Thinking in Java");
        Object b2 = new Book2("1111", "Java in 24 hrs");
        System.out.println(b1.equals(b2));
        // implement equals method so that it will print true
    }
}
