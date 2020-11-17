package presentationBook;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

import applicationBook.BookService;
import entity.Book;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

@Named("bookBean")
@ViewScoped
public class BookBean implements Serializable {
    private String bookTitle;
    private String bookId;
    //private Book book = new Book();
    private Book book;
    private List<Book> booksAvailable;
    //private List<Book> selectedBooks;
    private String filter = "";
    private Book selectedBook=null;
    private List<Book> books=null;

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
        selectedBook=new Book();
        booksAvailable = bookService.getAllBooks(filter);
        //bookTitle = book.getBookTitle();
        //System.out.println("bookTitle" + bookTitle);

        //String bookIdParam = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("bookId");
        //System.out.println(bookIdParam);
    }

    public void select(Book e){
        System.out.println("select e.toString " + e.toString());
        book=e;
    }

    public void add() {
        //System.out.println("BookBean add");
        bookService.addBook(book);
        //return "success";
    }

    public String update(String value) {
        System.out.println("BookBean update book " + value);
        bookService.updateBook(value,book);
        return "success";
    }

    public String update2(String value) {
        System.out.println("BookBean update book " + value);
        bookService.updateBook(value, book);
        return "success";
    }

    public String update3(String value) {
        System.out.println("BookBean update book " + value);
        bookService.updateBook(value, book);
        return "success";
    }

    public String delete(String value){
        System.out.println("delete " + value);
        bookService.delete(value);
        return "success";
    }

    /*public void onRowSelect(SelectEvent<Book> event) {
        FacesMessage msg = new FacesMessage("Book Selected", event.getObject().getbookId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowUnselect(UnselectEvent<Book> event) {
        FacesMessage msg = new FacesMessage("Book Unselected", event.getObject().getbookId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/

    //1
    public Book getBook() {
        //System.out.println("presentationBook BookBean getBook" + book);
        return book;
    }
    //2
    public void setBook(Book book) {
        //System.out.println("presentationBook BookBean setBook" + book);
        this.book = book;
    }

    public String getBookTitle() {
        //System.out.println("return BookTitle" + bookTitle);
        return bookTitle;
    }

    public String getBookId() {
        //System.out.println("getBookId Bean" + bookId);
        return bookId;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public Book getSelectedBook() {
        System.out.println("getSelected Book " + selectedBook);
        return selectedBook;
    }


    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }

    public List<Book> getBooksAvailable() {
        return booksAvailable;
    }

    public void setBooksAvailable(List<Book> booksAvailable) {
        this.booksAvailable = booksAvailable;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    /*public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }*/

    public BookService getBookService() {
        return bookService;
    }

    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
}
