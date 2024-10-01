package exercises.java_class_design;

import java.util.*;

class Book {
    private String title, isbn;
    public boolean equals(Object o) {
        return (o instanceof Book && ((Book)o).isbn.equals(this.isbn));
    }

    // hashcode was not implemented: default is to give you a unique hashcode for every object

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

class BookStore {
    Map<Book, Integer> map = new HashMap<Book, Integer>();
    int getNumberOfCopies(Book b) {
        // map.get returns null if the object is not found
        // because the value of the map is Integer and the return type of this method is int
        // it will try to unbox it to an int and because it's a null, it cannot unbox that
        // if the return type of this method was Integer instead of int there would be no Exception and it would print null
        return map.get(b);
    }
    public void addBook(Book b, int numberofcopies) {
        map.put(b, numberofcopies);
    }
    // ... other useful methods.
}

public class Q2_1298 {

    static BookStore bs = new BookStore();

    public static void main(String[] args) {
        Book b = new Book();
        b.setIsbn("111");
        bs.addBook(b, 10);
        // in here it will find it because we are using the same reference
        System.out.println(bs.getNumberOfCopies(b));

        b = new Book(); b.setIsbn("111");
        // books are equivalent but because it's a new object the hashcode will be different since Book did not override hashCode method
        // it will not find it, even though isbn is the same
        // Exception java.lang.NullPointerException: Cannot invoke "java.lang.Integer.intValue()" because the return value of "java.util.Map.get(Object)" is null
        System.out.println(bs.getNumberOfCopies(b));
    }
}
