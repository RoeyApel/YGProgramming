public class myThread extends Thread {
    volatile Turn turn;
    int id;

    public myThread(int id, Turn turn) {
        super();
        this.id = id;
        this.turn = turn;
    }

    public void run() {
        int count = 3;

        while (count > 0) {
            if (turn.get() == id) {
                System.out.println(id);
                turn.inc();
                count--;
            }
        }
    }
}
