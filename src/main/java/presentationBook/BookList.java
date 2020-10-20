package presentationBook;



import applicationBook.BookService;
import entity.Book;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class BookList {
    private List<Book> booksAvailable;
    private String filter = "";

    @Inject
    private BookService bookService;


    @PostConstruct
    public void postConstruct() {
        booksAvailable = bookService.getAllBooks(filter);
    }

    public List<Book> getBooksAvailable() {
        return booksAvailable;
    }

}
