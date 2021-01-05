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
import java.util.ArrayList;
import java.util.List;

@Named("technologyReadBean")
@ViewScoped
public class TechnologyReadBean implements Serializable {
    private Technology technology = new Technology();
    private String filter = "";
    private String value;
    private List<Technology> list = new ArrayList<>();

    @Inject
    private transient TechnologyModel technologyModel;

    //@Inject
    //private transient TechnologyModel technologyModel;

    public TechnologyReadBean() {
    }

    @PostConstruct
    public void init(){
        System.out.println("PostConstruct TechnologyReadBean kalder findMenuDocument");
        findMenuDocument();
    }

    public void select(Technology e){
        System.out.println("select e.toString " + e.toString());
        technology=e;
    }

    private void findMenuDocument() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String id_string = request.getParameter("id");
        System.out.println("TechnologyReadBean mongoID fra Web URL " + id_string);

        technology = technologyModel.findMenuDocument(id_string);
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
