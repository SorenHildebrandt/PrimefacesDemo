import entity.Student;

import java.io.Serializable;
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

@Named("studentController")
@ViewScoped
public class StudentController implements Serializable {
    private int id;
    private String name;
    private String choice;
    private Student student = new Student();
    //private Student student;
    private List<Student> booksAvailable;
    private String filter = "";
    private Student selectedStudent=null;
    //private List<Student> students=null;

    @Inject
    private transient StudentFacade studentEJB;

    private List<Student> list = new ArrayList<>();
    private List<Student> students;

    public StudentController() {
    }

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        selectedStudent=new Student();
        booksAvailable = studentEJB.getAllBooks(filter);
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

    public void select(Student e){
        System.out.println("select e.toString " + e.toString());
        student=e;
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

    public List<Student> getBooksAvailable() {
        return booksAvailable;
    }

    public void setBooksAvailable(List<Student> booksAvailable) {
        this.booksAvailable = booksAvailable;
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }
}
