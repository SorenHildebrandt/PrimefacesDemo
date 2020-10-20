package entity;

public class Book {
    private int id;
    private String bookTitle;

    public Book() {
    }

    public Book(String bookTitle) {
        this.id = id;
        this.bookTitle = bookTitle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("setId " + id);
        this.id = id;
    }

    public String getBookTitle() {
        System.out.println("getBookTitle " + bookTitle);
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        System.out.println("setBookTitle " + bookTitle);
        this.bookTitle = bookTitle;
    }
}

