package presentationBook;
import applicationBook.BookService;
import entity.Book;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import dao.MongoDBPersonDAO;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

//@Named("bookList")
//@ViewScoped
public class BookList implements Serializable {
    private List<Book> booksAvailable;
    //private List<Book> selectedBooks;
    private String filter = "";
    private Book selectedBook=null;
    private List<Book> book=null;



    /*@Inject
    private BookService bookService;*/

    @Inject
    private transient BookService bookService;

    @PostConstruct
    public void postConstruct() {
        booksAvailable = bookService.getAllBooks(filter);
        selectedBook=new Book();
    }

    public void select(Book e){
        System.out.println("select e.toString " + e.toString());
        selectedBook=e;

    }

   /*public void onRowSelect(SelectEvent<Book> event) {
        FacesMessage msg = new FacesMessage("Book Selected", event.getObject().getbookId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }*/



    public void onRowSelect(SelectEvent<Book> event) {
        Book book = ((Book) event.getObject());
        System.out.println("object book1 " + book);
        FacesMessage msg = new FacesMessage("Book Selected", event.getObject().getbookId());
        System.out.println("object getBookId " + msg);

        //push the selected book into BookVO
        //bookVO.setBook(book);
        System.out.println("object book2 " + book);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("testbook.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onRowUnselect(UnselectEvent<Book> event) {
        FacesMessage msg = new FacesMessage("Book Unselected", event.getObject().getbookId());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<Book> getBooksAvailable() {
        return booksAvailable;
    }

    public Book getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Book selectedBook) {
        this.selectedBook = selectedBook;
    }
}
