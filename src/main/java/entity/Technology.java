package entity;

import java.io.Serializable;

public class Technology implements Serializable {
    private int id;
    //private String choice;
    private String technologyChoice;
    private String richText1;
    private String richText2;


    public Technology() {
    }

    public Technology(int id, String technologyChoice, String richText1) {
        this.id = id;
        this.technologyChoice = technologyChoice;
        this.richText1 = richText1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("setId " + id);
        this.id = id;
    }

    public String getTechnologyChoice() {
        return technologyChoice;
    }

    public void setTechnologyChoice(String technologyChoice) {
        System.out.println("settechnologyChoice " + technologyChoice);
        this.technologyChoice = technologyChoice;
    }

    public String getRichText1() {
        return richText1;
    }

    public void setRichText1(String richText1) {
        this.richText1 = richText1;
    }
}

