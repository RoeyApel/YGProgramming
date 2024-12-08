public class MyThread implements Runnable {
    private Thread thread;
    private Lamp lamp;
    private int i;

    public MyThread(Lamp lamp, int i) {
        thread = new Thread(this);
        this.lamp = lamp;
        this.i = i;
    }

    public void start() {
        thread.start();
    }

    @Override
    public void run() {
        lamp.turnOnAt(i);
    }

}
