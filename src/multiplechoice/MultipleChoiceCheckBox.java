package multiplechoice;

import javafx.scene.control.CheckBox;

public class MultipleChoiceCheckBox extends CheckBox {
    public Boolean correct;

    public MultipleChoiceCheckBox(String name, Boolean correct) {
        super(name);
        this.correct = correct;
    }
}
