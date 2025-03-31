public abstract class Bot {
    protected Computer computer;

    public Bot(Computer computer) {
        this.computer = computer;
    }

    abstract public void makeMove();
}
