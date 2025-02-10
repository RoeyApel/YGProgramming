public enum Directions {
    DOWN(1, 0), UP(-1, 0), LEFT(0, -1), RIGHT(0, 1);

    int i, j;

    Directions(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
