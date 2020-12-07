package entity;

import org.bson.types.ObjectId;

import java.io.Serializable;
import java.util.List;

public class Technology implements Serializable {
    private ObjectId _id;
    private int id_integer;
    private String richText1;
    private String richText2;
    private List <String> selectedTechnologies;

    public Technology() {
    }

    public Technology(ObjectId _id, int id_integer, String richText1, String richText2, List<String> selectedTechnologies) {
        this._id = _id;
        this.id_integer = id_integer;
        this.richText1 = richText1;
        this.richText2 = richText2;
        this.selectedTechnologies = selectedTechnologies;
    }

    /* public Technology(ObjectId _id, int id_integer, String richText1, List<String> selectedTechnologies) {
        this._id = _id;
        this.id_integer = id_integer;
        this.richText1 = richText1;
        this.selectedTechnologies = selectedTechnologies;
    }*/

    public ObjectId get_id() {
        return _id;
    }

    public void set_Id(ObjectId _id) {
        this._id = _id;
    }

    public int getId_integer() {
        System.out.println("getId_integer " + id_integer);
        return id_integer;
    }

    public void setId_integer(int id_integer) {
        System.out.println("setId_integer " + id_integer);
        this.id_integer = id_integer;
    }

    public String getRichText1() {
        System.out.println("getRichText1 " + richText1);
        return richText1;
    }

    public void setRichText1(String richText1) {
        System.out.println("setRichText1 " + richText1);
        this.richText1 = richText1;
    }

    public String getRichText2() {
        return richText2;
    }

    public void setRichText2(String richText2) {
        this.richText2 = richText2;
    }

    public List<String> getSelectedTechnologies() {
        System.out.println("getSelectedTechnologies " + selectedTechnologies);
        return selectedTechnologies;
    }

    public void setSelectedTechnologies(List<String> selectedTechnologies) {
        System.out.println("setSelectedTechnologies " + selectedTechnologies);
        this.selectedTechnologies = selectedTechnologies;
    }
}

