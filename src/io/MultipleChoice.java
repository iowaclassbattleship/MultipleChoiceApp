package io;

import io.models.AnswerModel;
import io.models.MultipleChoiceModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultipleChoice {
    private List<MultipleChoiceModel> questions = new ArrayList<>();

    public MultipleChoice(List<List<String>> questions) {
        this.questionStructureBuilder(questions);
    }

    public List<MultipleChoiceModel> getQuestions() {
        return this.questions;
    }

    private void questionStructureBuilder(List<List<String>> questions) {
        for (List<String> question : questions) {
            String title = question.get(0);
            question.remove(0);
            this.questions.add(new MultipleChoiceModel(title, this.buildAnswerList(question)));
        }
    }

    private List<AnswerModel> buildAnswerList(List<String> rawAnswers) {
        List<AnswerModel> answers = new ArrayList<>();
        for (String answer : rawAnswers) {
            if (answer.startsWith("+")) {
                answer = answer.substring(1);
                answers.add(new AnswerModel(answer, true));
            } else {
                answers.add(new AnswerModel(answer, false));
            }
        }
        Collections.shuffle(answers);
        return answers;
    }
}
