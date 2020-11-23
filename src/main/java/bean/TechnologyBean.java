package bean;

import entity.Technology;
import model.TechnologyModel;
import org.primefaces.event.UnselectEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
    private String richText1;
    private String technologyChoice;
    private Technology technology = new Technology();
    private List<Technology> technologyAvailable;
    private String filter = "";
    private Technology selectedTechnology =null;
    private List<String> cities;
    private String[] selectedCities;


    @Inject
    private transient TechnologyModel technologyModel;

    private List<Technology> list = new ArrayList<>();
    private List<Technology> technologies;

    public TechnologyBean() {
    }

    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities.add("Miami");
        cities.add("London");
        cities.add("Paris");
        cities.add("Istanbul");
        cities.add("Berlin");
        cities.add("Barcelona");
        cities.add("Rome");
        cities.add("Brasilia");
        cities.add("Amsterdam");
        System.out.println("technologyChoice " + technologyChoice);

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

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    public String[] getSelectedCities() {
        System.out.println("getSelectedCities  " + selectedCities);
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        System.out.println("setSelectedCities  " + selectedCities);
        this.selectedCities = selectedCities;
    }
}
