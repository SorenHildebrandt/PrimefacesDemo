package model;

import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CheckboxView {

    private String[] selectedConsoles;
    private String[] selectedConsoles2;
    private String[] selectedCities;
    private String[] selectedCities2;
    private List<String> cities;
    private List<SelectItem> cars;
    private String[] selectedCars;

    @PostConstruct
    public void init() {
        cities = new ArrayList<String>();
        cities.add("JSF");
        cities.add("Primefaces");
        cities.add("Java");
        cities.add("Mongo database");
        cities.add("Github");
        cities.add("Lotus Notes udvikling");
        cities.add("Forretningsspecialist");
        cities.add("Sharepoint");
        cities.add("Azure");
        cities.add("Visual studio");
        cities.add("AngularJS");

        cars = new ArrayList<>();
        SelectItemGroup germanCars = new SelectItemGroup("German Cars");
        germanCars.setSelectItems(new SelectItem[] {
                new SelectItem("BMW", "BMW"),
                new SelectItem("Mercedes", "Mercedes"),
                new SelectItem("Volkswagen", "Volkswagen")
        });

        SelectItemGroup americanCars = new SelectItemGroup("American Cars");
        americanCars.setSelectItems(new SelectItem[]{
                new SelectItem("Chrysler", "Chrysler"),
                new SelectItem("GM", "GM"),
                new SelectItem("Ford", "Ford")
        });

        cars.add(germanCars);
        cars.add(americanCars);
    }

    public String[] getSelectedConsoles() {
        return selectedConsoles;
    }

    public void setSelectedConsoles(String[] selectedConsoles) {
        this.selectedConsoles = selectedConsoles;
    }

    public String[] getSelectedCities() {
        return selectedCities;
    }

    public void setSelectedCities(String[] selectedCities) {
        this.selectedCities = selectedCities;
    }

    public String[] getSelectedCities2() {
        return selectedCities2;
    }

    public void setSelectedCities2(String[] selectedCities2) {
        this.selectedCities2 = selectedCities2;
    }

    public String[] getSelectedConsoles2() {
        return selectedConsoles2;
    }

    public void setSelectedConsoles2(String[] selectedConsoles2) {
        this.selectedConsoles2 = selectedConsoles2;
    }

    public List<String> getCities() {
        System.out.println("getCities" + cities);
        return cities;
    }

    public List<SelectItem> getCars() {
        return cars;
    }

    public void setCars(List<SelectItem> cars) {
        this.cars = cars;
    }

    public String[] getSelectedCars() {
        return selectedCars;
    }

    public void setSelectedCars(String[] selectedCars) {
        this.selectedCars = selectedCars;
    }

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }
}
