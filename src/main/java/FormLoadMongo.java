import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class FormLoadMongo implements Serializable {
    private final static String HOST = "localhost";
    private final static int PORT = 27017;
    public final static String DATABASE = "cvbank";
    public final static String COLLECTION = "biodata";

    //private List<Student> students;
    /*private Student selectedStudent;
    private List<Student> selectedStudents;*/
    List<Student> studentList = new ArrayList();
    //List<Car> carList = new ArrayList();

    @Inject
    private transient MongoClient mongoClient;

    @PostConstruct
    private void init(){
        System.out.println("formloadMongo Init");
        loadMongo();
        //List<Student> studentList = new ArrayList();
    }

    public void loadMongo() {
        System.out.println("loadmongo");
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        //MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        //MongoDatabase cvBankDB = mongoClient.getDatabase("cvbank");
        //MongoCollection<Document> biodataCollection = cvBankDB.getCollection("biodata");
        //DBCollection bioData = cvBankDB.getCollection("biodata");
        //DBCursor cursor = bioData.find();

        DB db = mongoClient.getDB("cvbank");

        DBCollection table = db.getCollection("biodata");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("choice", "One");

        DBCursor cursor = table.find();

        while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            Student student = new Student((String) document.get("choice"),
                    (String) document.get("name"));
            System.out.println(student);
            System.out.println(studentList);

            studentList.add(student);

        }
    }
    /*public List<Student> getStudentList() {
        return studentList;
    }*/

    /*public FormLoadMongo setStudentList(List<Student> studentList) {
        this.studentList = studentList;
        return this;
    }*/

    public List getStudentList() {
        return studentList;
    }

    public void setStudentList(List studentList) {
        this.studentList = studentList;
    }
}
