package entity;

public class Skills {
    private String id;
    private String skill;
    private String text1;
    private String text2;

    public Skills() {
    }

    public Skills(String id, String skill, String text1, String text2) {
        this.id = id;
        this.skill = skill;
        this.text1 = text1;
        this.text2 = text2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
