package entity;

import java.io.Serializable;
import java.util.List;

public class CheckBoxMenu implements Serializable {
    private List<String> selectedItems;

    public CheckBoxMenu() {
    }

    public CheckBoxMenu(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }


    public List<String> getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List<String> selectedItems) {
        this.selectedItems = selectedItems;
    }
}
