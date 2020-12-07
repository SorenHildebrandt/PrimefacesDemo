package bean;

import entity.Technology;
import model.TechnologyModel;
import org.primefaces.event.UnselectEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("technologyBean")
@ViewScoped
public class TechnologyBean implements Serializable {
    private Technology technology = new Technology();
    private String filter = "";
    private List<String> availableTechnologies;
    private List<String> selectedTechnologies;
    private List<Technology> list = new ArrayList<>();

    @Inject
    private transient TechnologyModel technologyModel;

    public TechnologyBean() {
    }

    @PostConstruct
    public void init() {
        System.out.println("postconstruct");
        availableTechnologies = Arrays.asList("Java", "JSF", "Primefaces", "Mongo database", "Lotus Notes", "SharePoint",
                "Azure");
        find();
    }

    public void create() {
        System.out.println("TechnologyBean create");
        technologyModel.create(technology);

    }

    public void createNew() {
        System.out.println("clear felter");
        System.out.println("create new");
        technology.setSelectedTechnologies(null);
        technology.setRichText1(null);
        technology.setId_integer(0);
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

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Technology> getList() {
        return list;
    }

    public void setList(List<Technology> list) {
        this.list = list;
    }

    public List<String> getAvailableTechnologies() {
        return availableTechnologies;
    }

    public void setAvailableTechnologies(List<String> availableTechnologies) {
        this.availableTechnologies = availableTechnologies;
    }
}
