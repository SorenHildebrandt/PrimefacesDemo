package entity;

import java.io.Serializable;

public class Technology implements Serializable {
    private int id;
    //private String choice;
    private String technologyChoice;
    private String name;

    public Technology() {
    }

    public Technology(int id, String technologyChoice, String name) {
        this.id = id;
        this.technologyChoice = technologyChoice;
        this.name = name;
    }

    /*public Student(String name, String choice) {
        this.id = id;
        this.choice = choice;
        this.name = name;
    }*/

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

    public String getName() {
        System.out.println("getName " + name);
        return name;
    }

    public void setName(String name) {
        System.out.println("setName " + name);
        this.name = name;
    }
}

