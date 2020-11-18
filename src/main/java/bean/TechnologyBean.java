package bean;

import entity.Technology;
import model.TechnologyModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Named("technologyBean")
@ViewScoped
public class TechnologyBean implements Serializable {
    private int id;
    private String name;
    private String TechnologyChoice;
    private Technology technology = new Technology();
    private List<Technology> technologyAvailable;
    private String filter = "";
    private Technology selectedTechnology =null;

    @Inject
    private transient TechnologyModel technologyModel;

    private List<Technology> list = new ArrayList<>();
    private List<Technology> technologies;

    public TechnologyBean() {

    }

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        selectedTechnology=new Technology();
        technologyAvailable = technologyModel.getAllTechnologies(filter);
        find();
    }

    public void create() {
        System.out.println("TechnologyBean create");
        technologyModel.create(technology);
        find();
    }

private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public String edit() {
        System.out.println(sessionMap);
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
        list = technologyModel.find(filter);
    }

    public void delete() {
        technologyModel.delete(technology);
        find();
    }

    public void select(Technology e){
        System.out.println("select e.toString " + e.toString());
        technology=e;
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

    public String getTechnologyChoice() {
        return TechnologyChoice;
    }

    public void setTechnologyChoice(String technologyChoice) {
        TechnologyChoice = technologyChoice;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public List<Technology> getTechnologyAvailable() {
        return technologyAvailable;
    }

    public void setTechnologyAvailable(List<Technology> technologyAvailable) {
        this.technologyAvailable = technologyAvailable;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public Technology getSelectedTechnology() {
        return selectedTechnology;
    }

    public void setSelectedTechnology(Technology selectedTechnology) {
        this.selectedTechnology = selectedTechnology;
    }

    public TechnologyModel getTechnologyModel() {
        return technologyModel;
    }

    public void setTechnologyModel(TechnologyModel technologyModel) {
        this.technologyModel = technologyModel;
    }

    public List<Technology> getList() {
        return list;
    }

    public void setList(List<Technology> list) {
        this.list = list;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
}
