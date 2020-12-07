package bean;

import entity.CheckBoxMenu;
import model.CheckboxMenuModel;
import model.TechnologyModel;
import org.primefaces.event.UnselectEvent;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("selectCheckboxMenuBean")
@ViewScoped
public class SelectCheckboxMenuBean implements Serializable {

    private List<String> selectedItems; // Note: List<String> and thus NOT String!
    private List<String> availableItems;
    private CheckBoxMenu checkBoxMenu = new CheckBoxMenu();

    @Inject
    private transient CheckboxMenuModel checkboxMenuModel;

    @PostConstruct
    public void init() {
        availableItems = Arrays.asList("one", "two", "three", "four", "five");
    }

    public void onItemUnselect(UnselectEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();

        FacesMessage msg = new FacesMessage();
        msg.setSummary("Item unselected: " + event.getObject().toString());
        msg.setSeverity(FacesMessage.SEVERITY_INFO);

        context.addMessage(null, msg);
    }

    public void submit() {
        System.out.println("Selected items: " + selectedItems);
        checkboxMenuModel.createSelectCheckBox(checkBoxMenu, selectedItems);
    }

    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }

    public List<String> getAvailableItems() {
        return availableItems;
    }

    public void setAvailableItems(List<String> availableItems) {
        this.availableItems = availableItems;
    }
}
