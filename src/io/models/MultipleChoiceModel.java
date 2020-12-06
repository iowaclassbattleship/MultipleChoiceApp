package io.models;

import java.util.List;

public class MultipleChoiceModel {
    public String title;
    public List<AnswerModel> answers;

    public MultipleChoiceModel(String title, List<AnswerModel> answers) {
        this.title = title;
        this.answers = answers;
    }
}
