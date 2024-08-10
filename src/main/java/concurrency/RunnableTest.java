package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunnableTest {

    public static void main(String[] args) {
        // Create an ExecutorService with a fixed thread pool executing one thread
        ExecutorService es = Executors.newSingleThreadExecutor();

        // execute the runnable task (asynchronously) - void run()
        es.execute(() -> System.out.println("Runnable example"));

        // shutdown the executor service otherwise this application will never terminate;
        // existing tasks will be allowed to complete but no new tasks accepeted
        es.shutdown();
    }
}
