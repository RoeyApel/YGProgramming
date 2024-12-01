public abstract class Book {
    private String name;
    private int maxPages;

    public Book(String name, int maxPages) {
        this.name = name;
        this.maxPages = maxPages;
    }

    @Override
    public String toString() {
        return "Book [name=" + name + ", maxPages=" + maxPages + "]";
    }

    public static void sort(Book[] books) {

    }
}
