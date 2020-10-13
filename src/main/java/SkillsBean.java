import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import entity.Skills;

@Named("skillsBean")
@ViewScoped
public class SkillsBean implements Serializable {
    private Skills skills = new Skills();

    @Inject
    private transient SkillFacade skillEjb;

    public SkillsBean() {
    }

    @PostConstruct
    private void init() {
        System.out.println("postconstruct");
        //find();
    }

    public void create() {
        System.out.println("StudenController create");
        skillEjb.create(skills);
        //find();
    }

    public Skills getSkills() {
        return skills;
    }

    public SkillsBean setSkills(Skills skills) {
        this.skills = skills;
        return this;
    }

    public SkillFacade getSkillEjb() {
        return skillEjb;
    }

    public SkillsBean setSkillEjb(SkillFacade skillEjb) {
        this.skillEjb = skillEjb;
        return this;
    }
}
