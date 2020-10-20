package applicationBook;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import entity.Book;
import entity.Student;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import presentationBook.BookList;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;


public class BookService extends HttpServlet {
    //List<Book> booktList = new ArrayList();

    @Inject
    private transient MongoClient mongoClient;

    public void addBook(Book book) {
        MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");
        if  (book!=null) {
            System.out.println("applicationBook.BookService addBook");
            Document d = new Document().append("id", book.getId())
                    .append("bookTitle",book.getBookTitle());
            System.out.println(d);
            collection.insertOne(d);
        }
    }

    public List<Book> getAllBooks(String filter){
        System.out.println("Studentfacade");
        List<Book> list = new ArrayList<>();
        System.out.println("BookService" + list);
        MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("BookService filter" + list);
            iter = collection.find();
            System.out.println("BookService iter" + iter);
        } else {
            System.out.println("BookService iter else");
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("name", bsonRegex);
            iter = collection.find(bsonDoc);

        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                System.out.println("Studentfacade from json");
                list.add(new Gson().fromJson(doc.toJson(), Book.class));
            }
        });
        System.out.println(list);
        return list;
    }

    public void update(Book book) {
        //entityManager.merge(book);
    }
}
