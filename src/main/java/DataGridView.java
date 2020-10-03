import org.primefaces.PrimeFaces;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Sides;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class DataGridView implements Serializable {

    private List<Car> cars;

    private Car selectedCar;

    @Inject
    private CarService service;

    @PostConstruct
    public void init() {
        System.out.println("DataGridView Postconstruct init");
        cars = service.createCars(48);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setService(CarService service) {
        System.out.println("");
        this.service = service;
    }

    public Car getSelectedCar() {
        System.out.println("DataGridView getSelectedCar");
        return selectedCar;
    }

    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }

    public void clearMultiViewState() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        PrimeFaces.current().multiViewState().clearAll(viewId, true, (clientId) -> {showMessage(clientId);
        });
    }

    private void showMessage(String clientId) {
        FacesContext.getCurrentInstance()
                .addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, clientId + " multiview state has been cleared out", null));
    }
}
