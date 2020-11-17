package applicationBook;

import com.google.gson.Gson;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import converter.BookConverter;
import entity.Book;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import org.bson.json.JsonWriterSettings;


public class BookService extends HttpServlet{
    //List<Book> booktList = new ArrayList(h);
    private DBCollection col;
    private String bookTitle;

    @Inject
    private transient MongoClient mongoClient;

    //Save one book
    public void addBook(Book book) {
        MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");
        if  (book!=null) {
            System.out.println("applicationBook.BookService addBook");
            Document d = new Document().append("bookId", book.getbookId())
                    .append("bookTitle",book.getBookTitle());
            System.out.println(d);
            collection.insertOne(d);
        }
    }

    public List<Book> getAllBooks(String filter){
        System.out.println("Get all books in a list");
        List<Book> list = new ArrayList<>();
        //System.out.println("BookService " + bookTitle);
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
                System.out.println("BookService from json");
                list.add(new Gson().fromJson(doc.toJson(), Book.class));
            }
        });
        System.out.println("liste" + list);
        return list;
    }

    public void updateBook(String value, Book book) {
        JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();
        MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");
        System.out.println("BookService update book " + value);

        // update one document
        Bson filter = eq("bookId", value);

        Bson updateOperation = set("bookTitle", book.getBookTitle());

        UpdateResult updateResult = collection.updateOne(filter, updateOperation);
        System.out.println("=> Updating the doc with {\"BookId\":value}. Adding comment.");
        System.out.println(collection.find(filter).first().toJson(prettyPrint));
        System.out.println(updateResult);
    }


    public void delete(String value) {
        System.out.println("BookService delete " + value );
        MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");

        // delete one document
        Bson filter = eq("bookId", value);
        DeleteResult result = collection.deleteOne(filter);
        System.out.println(result);
    }
}
