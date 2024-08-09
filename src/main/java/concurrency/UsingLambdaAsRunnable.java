package concurrency;

import javax.swing.plaf.TableHeaderUI;

public class UsingLambdaAsRunnable {
    public static void main(String[] args) {
        Thread t = new Thread( () -> System.out.println("run(): " +
                Thread.currentThread().getName()));

        t.start();  // prints run(): Thread-0
//        t.run(); // no new thread is created and the run method will execute in the current thread, prints run(): main
        System.out.println("main(): " + Thread.currentThread().getName());
    }
}
