package bean;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import entity.Student;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//import com.sun.faces.context.flash.ELFlash;

//@Model
@Named
@ViewScoped
public class FormSaveMongo implements Serializable {
    private String name;
    private String resultChoice;
    private String result;
    private String choice;
    private String text;
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    public final static String DATABASE = "cvbank";
    public final static String COLLECTION = "biodata";

    List<Student> studentList = new ArrayList();

    //List<CvBank> CvBankList = new ArrayList();

  /* @Inject
   MongoClient mongoClient;*/


    @PostConstruct
    private void init(){
        System.out.println("formSaveMongo Init");
        List<Student> studentList = new ArrayList();
    }

    /*@PreDestroy
    private void destroyFormSaveMongo(){
        System.out.println("formSaveMongo destroy bean");
    }*/

   /* public void loadMongo() {
        System.out.println("loadmongo");
        DB db = mongoClient.getDB("wildflyschema");

        DBCollection table = db.getCollection("cars");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("manufacturer", "Ferrari");

        DBCursor cursor = table.find();

        while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            Student student = new Student((String) document.get("choice"));
            document.get("name");
            studentList.add(student);

        }
*/
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
       /* MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        DB db = mongoClient.getDB("cvbank");
        DBCollection bioData = db.getCollection("biodata");
        DBCursor cursor = bioData.find();

        while (cursor.hasNext()){
            System.out.println("loadmongo while cursor.hasNext");
            BasicDBObject document = (BasicDBObject) cursor.next();
            Student student = new Student((String) document.get("choice"));
            document.get("name");
            System.out.println(student);
            studentList.add(student);
        }*/
        //DB db = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);

    //}

    public void submit() {
        result = "Submitted values: " + name + ", " + choice;
        resultChoice = choice;
        System.out.println(result);
        Logger.getLogger("org.mongodb.driver").setLevel(Level.WARNING);
        System.out.println("1. Er i metoden submit");
        MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoDatabase cvBankDB = mongoClient.getDatabase("cvbank");
        MongoCollection<Document> biodataCollection = cvBankDB.getCollection("biodata");
        insertOneDocument(biodataCollection);
    }

    private void insertOneDocument(MongoCollection<Document> biodataCollection) {
        System.out.println("1. Er i metoden insertOneDocument");
        biodataCollection.insertOne(generateNewId(resultChoice, name));
    }

    //Let's make a grade factory method.
    private static Document generateNewId(String resultChoice, String name){
        System.out.println("1. Er i metoden generateNewId");
        return new Document("_id", new ObjectId()).append("choice", resultChoice)
                .append("name", name);
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void handleKeyEvent() {
        text = text.toUpperCase();
    }

}
