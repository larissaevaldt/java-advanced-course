package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceTypes {

    public static void main(String[] args) {
        // CachedThreadPool
        ExecutorService es = Executors.newCachedThreadPool();

        // FixedThreadPool
        int cpuCount = Runtime.getRuntime().availableProcessors();
        System.out.println(cpuCount);
        ExecutorService es2 = Executors.newFixedThreadPool(cpuCount);

        // SingleThreadPool
        ExecutorService es3 = Executors.newSingleThreadExecutor();
    }
}
