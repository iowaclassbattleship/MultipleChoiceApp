package io.models;

public class AnswerModel {
    public String key;
    public Boolean correct;

    public AnswerModel(String key, Boolean correct) {
        this.key = key;
        this.correct = correct;
    }
}
