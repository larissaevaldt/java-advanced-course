package org.example.collections;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class UsingQueues {

    public static void main(String[] args) {
        linkedListQueue();
        arrayDeque();
        priorityQueueNaturalOrdering();
        priorityQueueDifferentOrdering();
    }

    public static void linkedListQueue() {
        // A FIFO queue (First In First Out)
        Queue<Integer> queue = new LinkedList<>();
        // add() inserts into queue (throws exception if no space exists - if capacity is restricted)
        // offer() inserts into queue (returns false if no space exists - if capacity is restricted)
        queue.add(1);       // Head -> [1]
        queue.offer(2);  // Head -> [1, 2]
        queue.add(3);       // Head -> [1, 2, 3]
        queue.offer(4);  // Head -> [1, 2, 3, 4]
        // element() retrieves but does not remove the head od the queue (throws exception if queue is empty)
        // peek() retrieves but does not remove the head od the queue (returns null if queue is empty)
        System.out.println(queue.element());  // 1
        System.out.println(queue.peek());     // 1
        System.out.println(queue);            // [1, 2, 3, 4]
        // remove() - retrieves and removes the head of the queue (throws exception if queue is empty)
        // poll() - retrieves and removes the head of the queue (returns null if queue is empty)
        System.out.println(queue.remove());  // 1    Head-> [2, 3, 4]
        System.out.println(queue.poll());    // 2    Head-> [3, 4]
        System.out.println(queue);           // [3, 4]

        // offer()/poll() and peek() are the preferred methods as they do not throw exceptions (P.O.P)
    }

    public static void arrayDeque() {
        // Deque = "doubly ended queue". Supports element insertion/removal at both ends.
        // ArrayDeque - resizable-array implementation of the Deque interface (no capacity
        //              restrictions)
        Deque<Integer> numbers = new ArrayDeque<>();
        // "arg" (as in main(String[] ARGs))
            // Deque methods that begin with "a", "r" or "g" e.g. addFirst(), addLast(),
            // removeFirst(), removeLast(), getFirst() and getLast() all throw exception if
            // the deque is both capacity-constrained and full.
            // The other methods (POP): peekFirst(), peekLast(), offerFirst(), offerLast(), pollFirst(), pollLast();
            // rather than throw an exception in the same situation, they return null/false.
        // add at front (the head)
        numbers.add(1);             // Head -> [1] <- Tail
        numbers.addFirst(2);     // Head -> [2, 1] <- Tail      - exception thrown if deque is full
        numbers.offerFirst(3);   // Head -> [3, 2, 1] <- Tail   - null/false if deque is full
        System.out.println("Head: " + numbers.getFirst() + ". Head: " + numbers.peekFirst());  // Head: 3. Head: 3

        // add at the end (the tail)
        numbers.addLast(4);      // Head -> [3, 2, 1, 4] <- Tail
        numbers.offerLast(5);    // Head -> [3, 2, 1, 4, 5] <- Tail

        // remove from both ends
        numbers.removeFirst();     // Head -> [2, 1, 4, 5] <- Tail
        numbers.pollFirst();       // Head -> [1, 4, 5] <- Tail
        numbers.removeLast();      // Head -> [1, 4] <- Tail
        numbers.pollLast();        // Head -> [1] <- Tail
        System.out.println(numbers);  // [1]

        // The common peek()/offer()/poll() in use:
        System.out.println(numbers.offer(11));   // true    Head -> [1, 11] <- Tail
        System.out.println(numbers.offer(12));   // true    Head -> [1, 11, 12] <- Tail
        System.out.println(numbers.peek());         // 1       Head -> [1, 11, 12] <- Tail
        System.out.println(numbers.poll());         // 1       Head -> [11, 12] <- Tail
        System.out.println(numbers.poll());         // 11      Head -> [12] <- Tail
        System.out.println(numbers.poll());         // 12      Head -> [] <- Tail
        System.out.println(numbers.poll());         // null    Head -> [] <- Tail
    }

    public static void priorityQueueNaturalOrdering() {
        // Natural Ordering
        Queue<String> names = new PriorityQueue<>(); // alphabetical ordering
        names.add("V");
        names.add("P");
        names.add("A");
        Iterator itNames = names.iterator();
        while (itNames.hasNext()) {
            System.out.println(names.poll() + " ");  // A P V
        }

        Queue<Integer> numbers = new PriorityQueue<>(); // alphabetical ordering
        numbers.add(11);
        numbers.add(5);
        numbers.add(2);
        Iterator itNumbers = numbers.iterator();
        while (itNumbers.hasNext()) {
            System.out.println(numbers.poll() + " ");  // 2 5 11
        }
     }

    public static void priorityQueueDifferentOrdering() {
        // ordering specified by a comparator at construction time
        // 1. order by the title of the book
        // Comparator.comparing(Function)
        // API: "accepts a function that extracts a Comparable sort key from a type T,
        //       and returns a Comparator<T> that compares by that sort key.
        //  Function<T, R>
        //     R apply(T t)
        Comparator<Book> comparatorTitle = Comparator.comparing(book -> book.getTitle());
        //Comparator<Book> comparatorTitle = Comparator.comparing(book::getTitle());
        Queue<Book> booksByTitle = new PriorityQueue<>(comparatorTitle); // order by title
        booksByTitle.add(new Book("Java", 55.0));  // note that String implements the Comparable interface
        booksByTitle.add(new Book("Python", 23.0));
        booksByTitle.add(new Book("C++", 99.0 ));

        System.out.println("Ordering by title:");
        Iterator itBooks = booksByTitle.iterator();
        while (itBooks.hasNext()) {
            Book book = booksByTitle.poll();
            System.out.println(book);
            /*
             Book{title='C++'}
             Book{title='Java'}
             Book{title='Python'}
            */
        }

        // 2. Order by the price of the book
        Comparator<Book> comparatorPrice = Comparator.comparing(Book::getPrice);
        Queue<Book> booksByPrice = new PriorityQueue<>(comparatorPrice); // order by price
        booksByPrice.add(new Book("Java", 55.0));  // note that Double implements the Comparable interface
        booksByPrice.add(new Book("Python", 23.0));
        booksByPrice.add(new Book("C++", 99.0 ));

        System.out.println("Ordering by price:");
        Iterator itMoreBooks = booksByPrice.iterator();
        while (itMoreBooks.hasNext()) {
            Book book = booksByPrice.poll();
            System.out.println(book);
            /*
             Book{title='Python', price=23.0}
             Book{title='Java', price=55.0}
             Book{title='C++', price=99.0}
            */
        }
    }
}

class Book {
    String title;  // implements Comparable
    Double price;  // implements Comparable

    public Book(String title, Double price) {
        this.title = title;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public Double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
