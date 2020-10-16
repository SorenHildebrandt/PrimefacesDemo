import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import dao.MongoDBPersonDAO;
import entity.Student;
import org.bson.BsonDocument;
import org.bson.BsonRegularExpression;
import org.bson.Document;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

//@Stateless
@WebServlet("/addPerson")
public class StudentFacade extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(StudentFacade.class.getName());
    private final static String HOST = "localhost";
    private final static int PORT = 27017;

    public final static String DATABASE = "cvbank";
    public final static String COLLECTION = "biodata";

    @Inject
    private transient MongoClient mongoClient;

//    public MongoClient mongoClient() {
//        return new MongoClient(new ServerAddress(HOST, PORT));
//    }

    /*protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("doPost");
        String choice = request.getParameter("choice");
        String name = request.getParameter("name");
        System.out.println("doPost" + "Navn " + name + " choice " + choice);
        if ((name == null || name.equals(""))
                || (choice == null || choice.equals(""))) {
            request.setAttribute("error", "Mandatory Parameters Missing");
            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/index.xhtml");
            rd.forward(request, response);
        } else {
            Student p = new Student();
            p.setChoice(choice);
            p.setName(name);
            MongoClient mongo = (MongoClient) request.getServletContext()
                    .getAttribute("MONGO_CLIENT");
            MongoDBPersonDAO personDAO = new MongoDBPersonDAO(mongo);
            personDAO.createPerson(p);
            System.out.println("Person Added Successfully with id="+p.getId());
            request.setAttribute("success", "Person Added Successfully");
            List<Student> students = personDAO.readAllStudent();
            request.setAttribute("persons", persons);


            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/persons.jsp");
            rd.forward(request, response);
        }
    }
*/

    public void create(Student student) {
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        MongoCollection<Document> collection = mongoClient.getDatabase("cvbank").getCollection("biodata");
        if  (student!=null) {

            Document d = new Document().append("id", student.getId())
                    .append("choice", student.getChoice())
                    .append("name", student.getName());
            collection.insertOne(d);
        }
    }



    public void delete(Student student) {
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        //MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase("cvbank").getCollection("biodata");
        collection.deleteOne(new Document("id", student.getId()));
    }

    public List<Student> find(String filter) {
        System.out.println("Studentfacade");
        List<Student> list = new ArrayList<>();
        System.out.println("Studentfacade" + list);
        //MongoClient mongoClient = new MongoClient(new ServerAddress(HOST, PORT));
        //MongoCollection<Document> collection = mongoClient.getDatabase(DATABASE).getCollection(COLLECTION);
        MongoCollection<Document> collection = mongoClient.getDatabase("cvbank").getCollection("biodata");
        FindIterable<Document> iter;
        if (filter == null || filter.trim().length() == 0) {
            System.out.println("Studentfacade filter" + list);
            iter = collection.find();
            System.out.println("Studentfacade iter" + iter);
        } else {
            System.out.println("Studentfacade iter else");

            BsonRegularExpression bsonRegex = new BsonRegularExpression(filter);
            BsonDocument bsonDoc = new BsonDocument();
            bsonDoc.put("name", bsonRegex);
            iter = collection.find(bsonDoc);

        }
        iter.forEach(new Block<Document>() {
            @Override
            public void apply(Document doc) {
                System.out.println("Studentfacade from json");
                list.add(new Gson().fromJson(doc.toJson(), Student.class));
            }
        });
        return list;
    }
}
