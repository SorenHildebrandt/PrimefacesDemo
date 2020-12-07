package model;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import entity.CheckBoxMenu;
import org.bson.Document;

import javax.inject.Inject;
import java.util.List;

public class CheckboxMenuModel {
    //private List<String> selectedItems;

    @Inject
    private transient MongoClient mongoClient;

    public void createSelectCheckBox(CheckBoxMenu checkBoxMenu, List<String> selectedItems) {
        System.out.println("createSelectCheckBox");

        //this.selectedItems = checkBoxMenu.getSelectedItems();
        System.out.println("Selected items " + selectedItems);

        MongoCollection<Document> collection = mongoClient.getDatabase("hildebrandt-udvikling").getCollection("technology");

        System.out.println("id er 0 vi gemmer et nyt dokument");
        Document d = new Document().append("selectedItems", selectedItems);
        collection.insertOne(d);
    }
}
