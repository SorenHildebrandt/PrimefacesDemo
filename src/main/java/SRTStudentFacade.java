import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import entity.Student;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@Stateless
public class SRTStudentFacade {
    private static final Logger LOGGER = Logger.getLogger(SRTStudentFacade.class.getName());
    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public final static String DATABASE = "cvbank";
    public final static String COLLECTION = "biodata";

    @Inject
    private transient MongoClient mongoClient;

    public MongoClient mongoClient() {
        return new MongoClient(new ServerAddress(HOST, PORT));
    }

    public void create(Student student) {
        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        if  (student!=null) {

            Document d = new Document().append("id", student.getId())
                    .append("choice", student.getChoice())
                    .append("name", student.getName());
            collection.insertOne(d);
        }
    }



    public void delete(Student student) {
        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        collection.deleteOne(new Document("id", student.getId()));
    }

    public List<Student> find(String filter) {
        System.out.println("Studentfacade");
        List<Student> list = new ArrayList<>();
        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            iter = collection.find();
        } else {

            BsonRegularExpression bsonRegex = new
                    BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("name", bsonRegex);
            iter = collection.find(bsonDoc);

        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                list.add(new Gson().fromJson(doc.toJson(), Student.class));
            }
        });
        return list;
    }
}
