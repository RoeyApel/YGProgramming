public class Move {
    private Position current;
    private Position target;

    public Move(Position current, Position target) {
        this.current = current;
        this.target = target;
    }

    public Move(int rowCur, int colCur, int rowTarg, int colTarg) {
        current = new Position(rowCur, colCur);
        target = new Position(rowTarg, colTarg);
    }

    public Position getCurrent() {
        return current;
    }

    public void setCurrent(Position current) {
        this.current = current;
    }

    public Position getTarget() {
        return target;
    }

    public void setTarget(Position target) {
        this.target = target;
    }

}
