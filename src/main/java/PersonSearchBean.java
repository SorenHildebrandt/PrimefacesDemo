import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("personSearchBean")
@ViewScoped
public class PersonSearchBean implements Serializable {
    public void test(){
        System.out.println("test");
    }
}
