import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named("listernerView")
@RequestScoped
public class ListernerView {

    private String text;

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public void handleKeyEvent() {
        text = text.toUpperCase();
    }

}
