import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;


//@Named("studentController")
//@SessionScoped
@Named("studentController")
@ViewScoped
public class StudentController implements Serializable {
    private Student student = new Student();
    private String filter = "";

    @Inject
    private transient StudentFacade studentEJB;
    private List<Student> list = new ArrayList<>();

    public StudentController() {
    }

    @PostConstruct
    private void init() {
        find();
    }

    public void create() {
        System.out.println("StudenController create");
        studentEJB.create(student);
        find();
    }

    public void find() {
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
