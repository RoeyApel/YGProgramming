public class Turn {
    int turn;
    int count;

    public Turn(int turn, int count) {
        this.turn = turn;
        this.count = count;
    }

    public int get() {
        return turn;
    }

    public void set(int turn) {
        this.turn = turn;
    }

    public void inc() {
        turn++;
        turn = turn % count;
    }

    public void dec() {
        turn--;
    }
}
