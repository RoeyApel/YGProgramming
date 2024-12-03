public class Thriller extends Book {

    private int pages;

    public Thriller(String name, int maxPages) {
        super(name, maxPages);
    }

    @Override
    public void read(int pages) throws TooManyPages {
        if (pages > getMaxPages()) {
            throw new TooManyPages();
        }
    }

}
