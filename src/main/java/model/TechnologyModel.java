package model;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.UpdateResult;
import entity.Technology;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;
import org.bson.conversions.Bson;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import org.bson.json.JsonWriterSettings;

//@Stateless
//@WebServlet("/addPerson")
public class TechnologyModel extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TechnologyModel.class.getName());
    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public final static String DATABASE = "hildebrandt-udvikling";
    public final static String COLLECTION = "technology";
    private DBCollection col;
    private String richText1;
    //private String choice;
    private String technologyChoice;
    private Integer id;
    //private String id_string;

    @Inject
    private transient MongoClient mongoClient;


    public void create(Technology technology) {
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        richText1 = technology.getRichText1();
        id = technology.getId();
        technologyChoice = technology.getTechnologyChoice();
        //id_string = String.valueOf(id);


        System.out.println("id " + id + " " + "richText " + richText1  );

        if  (id != 0) {
            System.out.println("id er forskellig fra nul og eks. dokument skal opdaterets");
            JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

            // update one document
            Bson filter = eq("id", id);
            System.out.println("Filter " + filter);
            //Bson updateOperation = set("choice", student.getChoice());
            Bson updateOperation = combine(set("id", id), set("richText1", richText1), set("technologyChoice", technologyChoice));
            System.out.println("updateOperation " + updateOperation);

            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            System.out.println(collection.find(filter).first().toJson(prettyPrint));
            System.out.println(updateResult);
        } else {
            System.out.println("id er 0 vi gemmer et nyt dokument");
            Document d = new Document().append("id", technology.getId())
                    .append("technologyChoice", technology.getTechnologyChoice())
                    .append("richText1;", technology.getRichText1());
            collection.insertOne(d);
        }

    }

    public void delete(Technology technology) {
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        //MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        collection.deleteOne(new Document("id", technology.getId()));
    }

    public List<Technology> find(String filter) {
        System.out.println("Studentfacade");
        List<Technology> list = new ArrayList<>();
        System.out.println("Studentfacade" + list);
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("Studentfacade filter" + list);
            iter = collection.find();
            System.out.println("Studentfacade iter" + iter);
        } else {
            System.out.println("Studentfacade iter else");
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("richText1", bsonRegex);
            iter = collection.find(bsonDoc);

        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                System.out.println("Studentfacade from json");
                list.add(new Gson().fromJson(doc.toJson(), Technology.class));
            }
        });
        System.out.println(list);
        return list;
    }

    public void edit(Technology technology) {
        //String id = request.getParameter("id");
    }

    public List<Technology> getAllTechnologies(String filter) {
        System.out.println("Get all books in a list");
        List<Technology> list = new ArrayList<>();
        //System.out.println("BookService " + bookTitle);
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("BookService filter" + list);
            iter = collection.find();
            System.out.println("BookService iter" + iter);
        } else {
            System.out.println("BookService iter else");
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("richText1", bsonRegex);
            iter = collection.find(bsonDoc);
        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                System.out.println("BookService from json");
                list.add(new Gson().fromJson(doc.toJson(), Technology.class));
            }
        });
        System.out.println("liste  " + list);
        return list;
    }
}
