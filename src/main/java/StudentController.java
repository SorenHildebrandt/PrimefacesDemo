import com.mongodb.MongoClient;
import dao.MongoDBPersonDAO;
import entity.Student;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@Named("studentController")
//@SessionScoped
@Named("studentController")
@ViewScoped
public class StudentController extends HttpServlet {
    private Student student = new Student();
    private String filter = "";

    @Inject
    private transient StudentFacade studentEJB;

    private List<Student> list = new ArrayList<>();

    public StudentController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        find();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            //personDAO.createPerson(p);
            System.out.println("Person Added Successfully with id="+p.getId());
            request.setAttribute("success", "Person Added Successfully");
            //List<Student> students = personDAO.readAllStudent();
            //request.setAttribute("persons", persons);


            RequestDispatcher rd = getServletContext().getRequestDispatcher(
                    "/persons.jsp");
            rd.forward(request, response);
        }
    }


    public void create() {
        System.out.println("StudenController create");
        studentEJB.create(student);
        find();
    }

    public void find() {
        System.out.println("find");
        list = studentEJB.find(filter);
    }

    public void delete() {
        studentEJB.delete(student);
        find();
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public StudentFacade getStudentEJB() {
        return studentEJB;
    }

    public StudentController setStudentEJB(StudentFacade studentEJB) {
        this.studentEJB = studentEJB;
        return this;
    }

    public List<Student> getList() {
        return list;
    }

    public StudentController setList(List<Student> list) {
        this.list = list;
        return this;
    }
}
