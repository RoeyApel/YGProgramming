import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static AtomicInteger flag = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        Thread thread = new Thread(new Run());
        thread.start();

        int count = 6;
        while (count != 0) {
            if (flag.compareAndSet(0, 1)) {
                System.out.println("main Thread");
                count--;
            } else {
                Thread.yield();
            }
        }
    }

    static class Run implements Runnable {
        @Override
        public void run() {
            int count = 6;

            while (count != 0) {

                if (flag.compareAndSet(1, 0)) {
                    System.out.println("bg Thread");
                    count--;
                } else {
                    Thread.yield();
                }
            }
        }

    }
}
