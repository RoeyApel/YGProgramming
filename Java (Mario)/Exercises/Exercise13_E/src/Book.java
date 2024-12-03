import java.util.Arrays;

public abstract class Book implements Readable {
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
        Arrays.sort(books, (b1, b2) -> b1.name.compareTo(b2.name));
    }

    public String getName() {
        return name;
    }

    public int getMaxPages() {
        return maxPages;
    }

}
