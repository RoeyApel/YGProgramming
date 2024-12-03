import java.util.Arrays;

public class Library {
    public static void main(String[] args) {
        Book[] books = {
                new Thriller("The Great Gatsby", 180),
                new Novel("1984", 328),
                new ScienceFiction("Moby Dick", 635),
        };

        Book.sort(books);
        System.out.println(Arrays.toString(books));
        try {
            books[2].read(340);
        } catch (TooManyPages e) {
            System.err.println("Too many pages man slow down like it a small book..");
        }
        System.out.println(books[2].toString());
    }
}
