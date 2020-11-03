package converter;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import entity.Book;
import entity.Skills;
import org.bson.types.ObjectId;

public class BookConverter {
    // convert Person Object to MongoDB DBObject
    // take special note of converting id String to ObjectId
    public static DBObject toDBObject(Book book) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("bookId", book.getbookId()).append("", book.getBookTitle());
        if (book.getId() != null)
            builder = builder.append("_id", new ObjectId(book.getId()));
        return builder.get();
    }

    // convert DBObject Object to Person
    // take special note of converting ObjectId to String
    public static Book toBook(DBObject doc) {
        Book book = new Book();
        book.setbookId((String) doc.get("bookId"));
        book.setBookTitle((String) doc.get("bookTitle"));
        ObjectId id = (ObjectId) doc.get("_id");
        book.setId(id.toString());
        return book;
    }

}
