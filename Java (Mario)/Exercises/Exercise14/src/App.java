
class Flag {
    public static int f;

    public Flag() {
        f = 0;
    }
}

public class App {
    public static Flag flag = new Flag();

    public static void main(String[] args) throws Exception {
        Thread bThread = new BackgroundThead();
        bThread.start();
        int count = 6;

        while (count != 0) {
            if (flag.f == 0) {
                System.out.println("main Thread");
                flag.f = 1;
                count--;
            } else {
                Thread.yield();
            }
        }
    }

    static class BackgroundThead extends Thread {
        public void run() {
            int count = 6;
            while (count != 0) {
                if (flag.f == 1) {
                    System.out.println("bg Thread");
                    flag.f = 0;
                    count--;
                } else {
                    Thread.yield();
                }
            }
        }
    }
}
