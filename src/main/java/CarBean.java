import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("carBean")
@SessionScoped
public class CarBean implements Serializable {
    private List<Car> cars;

    public CarBean() {
        cars = new ArrayList<Car>();
        //cars.add(new Car("myModel",2005,"ManufacturerX","blue"));
        //add more cars
    }



    public String getMessage() {
        return "Hello World from Fuertefentura";
    }

    public List<Car> getCars() {
        return cars;
    }
}
