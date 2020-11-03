package entity;

import java.io.Serializable;

public class Book implements Serializable {
    private String id;
    private String bookId;
    private String bookTitle;

    public Book() {
    }

    public Book(String bookTitle) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getbookId() {
        return bookId;
    }

    public void setbookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        System.out.println("Book getBookTitle");
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}

