package concurrency;

// sleep() and join()
// sleep is often used for helping all threads to get a chance to run
class CountDown implements Runnable {

    String[] timeStr = {"Zero", "One", "Two", "Three", "Four", "Five",
            "Six", "Seven", "Eight", "Nine"};

    @Override
    public void run() {
        for (int i = 9; i >=0 ; i--) {
            try {
                // print with one-second delay between numbers
                System.out.println(timeStr[i]);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
public class TimeBomb {
    public static void main(String[] args) {
        Thread timer = new Thread(new CountDown());
        System.out.println("Starting the 10 second count down...");
        timer.start();
        // Make the main thread wait until the timer thread has finished
        try {
            timer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Boom!!!");
    }
}
