package tools;

public enum Colors {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001B[36m");

    final String color;

    Colors(String color) {
        this.color = color;
    }

    public String get() {
        return color;
    }
}
