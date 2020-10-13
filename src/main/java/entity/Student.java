package entity;

import java.util.Objects;

public class Student {
    private int id;
    private String choice;
    private String name;

    public Student() {

    }

    public Student(String name, String choice) {
        this.id = id;
        this.choice = choice;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public int getId() {
        return id;
    }

    public Student setId(int id) {
        this.id = id;
        return this;
    }*/

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

