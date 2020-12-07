package model;

import com.google.gson.Gson;
import com.mongodb.*;
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
import org.bson.types.ObjectId;

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
    private String richText2;
    private Integer id_integer;
    private List <String> selectedTechnologies;
    private ObjectId id;
    private Integer collectionCount_integer;

    @Inject
    private transient MongoClient mongoClient;

    //public void create(Technology technology, List<String> selectedTechnologies) {
    public void create(Technology technology) {

        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");

        collectionCount_integer = Math.toIntExact(collection.countDocuments());
        System.out.println(collection.countDocuments());
        System.out.println( "id_integer " + id_integer);

        System.out.println("ID" + id);
        richText1 = technology.getRichText1();
        richText2 = technology.getRichText2();

        id_integer = technology.getId_integer();
        selectedTechnologies = technology.getSelectedTechnologies();
        System.out.println("selectedTechnologies srt " + selectedTechnologies + " " + id_integer  );

        if  (id_integer != 0) {
            System.out.println("id_INTEGER er forskellig fra nul og eks. dokument skal opdaterets");
            JsonWriterSettings prettyPrint = JsonWriterSettings.builder().indent(true).build();

            // update one document
            Bson filter = eq("id_integer", id_integer);
            System.out.println("Filter " + filter);
            //Bson updateOperation = set("choice", student.getChoice());
            Bson updateOperation = combine(set("id_integer", id_integer), set("richText1", richText1), set("richText2", richText2), set("selectedTechnologies", selectedTechnologies));
            System.out.println("updateOperation " + updateOperation);

            UpdateResult updateResult = collection.updateOne(filter, updateOperation);
            //System.out.println(collection.find(filter).first().toJson(prettyPrint));
            //System.out.println(updateResult);
        }

        if (id_integer == 0) {
            System.out.println("id_integer er 0 vi gemmer et nyt dokument");
            id_integer =  collectionCount_integer + 1;
            Document d = new Document().append("id_integer", id_integer)
                    .append("selectedTechnologies", technology.getSelectedTechnologies())
                    .append("richText1", technology.getRichText1())
                    .append("richText2", technology.getRichText2());

            collection.insertOne(d);
        }
    }


    public void delete(Technology technology) {
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        //MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        collection.deleteOne(new Document("id_integer", technology.getId_integer()));
    }

    public List<Technology> find(String filter) {
        System.out.println("Metode find i TechnologyModel");
        List<Technology> list = new ArrayList<>();
        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            iter = collection.find();
        } else {
            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("richText1", bsonRegex);
            iter = collection.find(bsonDoc);

        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                list.add(new Gson().fromJson(doc.toJson(), Technology.class));
            }
        });
        System.out.println("Listen i metoden find  " + list);
        return list;
    }
}
