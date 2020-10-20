import com.mongodb.MongoClient;
import dao.MongoDBPersonDAO;
import entity.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@Named("studentController")
//@SessionScoped
//@Named("studentController")
//@ViewScoped
//@ManagedBean
//@RequestScoped
@Named("studentController")
@ViewScoped
public class StudentController extends HttpServlet {
    private Student student = new Student();
    private String filter = "";

    @Inject
    private transient StudentFacade studentEJB;

    private List<Student> list = new ArrayList<>();
    private List<Student> students;

    public StudentController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        find();
    }

    public void create() {
        System.out.println("StudenController create");
        studentEJB.create(student);
        find();
    }

private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String edit() {
        System.out.println(sessionMap);
        System.out.println("StudenController edit");
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String,String> params = facesContext.getExternalContext().getRequestParameterMap();

        String id=params.get("action");
        System.out.println(facesContext);
        System.out.println(params);
        System.out.println(id);

        return null;
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) {
        System.out.println("doPost");
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
