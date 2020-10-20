package entity;

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
        System.out.println("setId " + id);
        this.id = id;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        System.out.println("setChoice " + choice);
        this.choice = choice;
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

