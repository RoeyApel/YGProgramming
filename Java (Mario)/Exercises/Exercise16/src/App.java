public class App {
    public static void main(String[] args) throws Exception {
        MyThread[] threads = new MyThread[32];
        Lamp lamp = new Lamp();

        for (int i = 31; i >= 0; i--) {
            MyThread thread = new MyThread(lamp, i);
            threads[i] = thread;
            thread.start();
        }
    }
}
