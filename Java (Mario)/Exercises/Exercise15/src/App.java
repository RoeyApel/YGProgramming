import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static void main(String[] args) throws Exception {
        int numOfThreads = 10;

        Turn turn = new Turn(0, numOfThreads);

        ArrayList<myThread> threads = new ArrayList<>();

        for (int i = 0; i < numOfThreads; i++) {
            myThread thread = new myThread(i, turn);
            threads.add(thread);
        }
        for (myThread thread : threads) {
            thread.start();
        }
    }
}
