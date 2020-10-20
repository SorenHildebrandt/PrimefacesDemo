package presentationBook;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

import applicationBook.BookService;
import entity.Book;

@Named(value = "bookBean")
@ViewScoped
public class BookBean implements Serializable {
    private String bookTitle;
    private Integer bookId;
    private Book book;

    public BookBean() {
    }

    /*private List<Book> list = new ArrayList<>();
    private List<Book> books;*/

    @Inject
    private transient BookService bookService;

    @PostConstruct
    public void init(){
        System.out.println("bookBean postconstruct");
        book = new Book();
        //String bookIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookId");
        //System.out.println(bookIdParam);
    }

    public String add() {
        System.out.println("presentationBook.BookBean add");
        bookService.addBook(book);
        return "success";
    }

    public String update() {
        book.setBookTitle(bookTitle);
        bookService.update(book);
        return "success";
    }

    //1
    public Book getBook() {
        System.out.println("presentationBook.BookBean getBook" + book);
        return book;
    }
    //2
    public void setBook(Book book) {
        System.out.println("presentationBook.BookBean setBook" + book);
        this.book = book;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}
