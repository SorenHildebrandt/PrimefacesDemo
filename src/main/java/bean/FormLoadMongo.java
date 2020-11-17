package bean;

import com.mongodb.*;
import entity.Student;

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

    List<Student> studentList = new ArrayList();

    @Inject
    private transient MongoClient mongoClient;

    @PostConstruct
    private void init(){
        System.out.println("formloadMongo Init");
        loadMongo();
    }

    public void loadMongo() {
        System.out.println("loadmongo");

        DB db = mongoClient.getDB("cvbank");
        DBCollection table = db.getCollection("biodata");

        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("choice", "One");

        DBCursor cursor = table.find();

       /* while (cursor.hasNext()) {
            BasicDBObject document = (BasicDBObject) cursor.next();
            Student student = new Student((String) document.get("choice"),
                    (String) document.get("name"));
            System.out.println(student);
            System.out.println(studentList);

            studentList.add(student);

        }*/
    }

    public List getStudentList() {
        return studentList;
    }

    public void setStudentList(List studentList) {
        this.studentList = studentList;
    }
}
