package dao;

import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import converter.BookConverter;
import com.mongodb.*;
import entity.Book;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import com.mongodb.MongoClient;

//DAO class for different MongoDB CRUD operations
//take special note of "id" String to ObjectId conversion and vice versa
//also take note of "_id" key for primary key

public class MongoDBPersonDAO extends HttpServlet{

	private DBCollection col;
	private FilterConfig request;
	//MongoClient mongo = (MongoClient) request.getServletContext().getAttribute("MONGO_CLIENT");
	//MongoDBPersonDAO personDAO = new MongoDBPersonDAO(mongo);

	@Inject
	private transient MongoClient mongoClient;


//	public MongoDBPersonDAO() {
//	}
//
//	public void setCol(DBCollection col) {
//		this.col = col;
//	}
//
//	public MongoDBPersonDAO(MongoClient mongo) {
//
//		this.col = mongo.getDB("bookstore").getCollection("books");
//	}

	public Book createBook(Book book) {
		MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");

		DBObject doc = BookConverter.toDBObject(book);
		this.col.insert(doc);
		ObjectId id = (ObjectId) doc.get("_id");
		book.setId(id.toString());
		return book;
	}

	/*public void addBook(Book book) {
		MongoCollection<Document> collection = mongoClient.getDatabase("bookstore").getCollection("books");
		if  (book!=null) {
			System.out.println("applicationBook.BookService addBook");
			Document d = new Document().append("bookId", book.getbookId())
					.append("bookTitle",book.getBookTitle());
			System.out.println(d);
			collection.insertOne(d);
		}
	}*/

	/*public void updatePerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.update(query, PersonConverter.toDBObject(p));
	}*/

	public void updateBook(Book book) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(book.getId())).get();
		this.col.update(query, BookConverter.toDBObject(book));
	}


	public List<Book> readAllPerson() {
		List<Book> data = new ArrayList<Book>();
		DBCursor cursor = col.find();
		while (cursor.hasNext()) {
			DBObject doc = cursor.next();
			Book book= BookConverter.toBook(doc);
			data.add(book);
		}
		return data;
	}

	/*public void deletePerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		this.col.remove(query);
	}

	public Person readPerson(Person p) {
		DBObject query = BasicDBObjectBuilder.start()
				.append("_id", new ObjectId(p.getId())).get();
		DBObject data = this.col.findOne(query);
		return PersonConverter.toPerson(data);
	}
*/

}
