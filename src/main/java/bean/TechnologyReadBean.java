package bean;

import entity.Technology;
import model.TechnologyModel;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Named("technologyReadBean")
@ViewScoped
public class TechnologyReadBean implements Serializable {
    private Technology technology = new Technology();
    private String filter = "";
    private String value;
    private List<Technology> list = new ArrayList<>();
    private String id_string;

    @Inject
    private transient TechnologyModel technologyModel;

    public TechnologyReadBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct TechnologyReadBean kalder findMenuDocument");
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        System.out.println("request " + request);
        String id_string = request.getParameter("id");
        System.out.println("id_string " + id_string);
        if (id_string != null){
            System.out.println("id_string er forskellig fra null " + id_string);
            findMenuDocument();
        }
        else
            findMenuById();

    }

    private void findMenuById() {
        System.out.println("findMenuById");
        String id = "5fca01f1d18cc80dabdf475d";
        System.out.println("id_string er blank");

        //technology.setId(request2);
        technology = technologyModel.findDocumentById(id);
    }


    public void select(Technology e){
        System.out.println("select e.toString " + e.toString());
        technology=e;
    }

    private void findMenuDocument() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        System.out.println("request " + request);
        String id_string = request.getParameter("id");
        System.out.println("id_string " + id_string);
        System.out.println("TechnologyReadBean mongoID fra Web URL " + id_string);
        technology.setId(id_string);
        technology = technologyModel.readTechnology(technology);

    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
