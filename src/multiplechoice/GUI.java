package multiplechoice;

import models.AnswerModel;
import models.MultipleChoiceModel;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.List;

public class GUI extends Application {
    private static final int WIDTH = 100;
    private static final int HEIGHT = 300;

    private List<MultipleChoiceModel> questions;
    private Stage stage;
    private int currentPos = 0;

    public void launchApp() {
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.questions = io.FileReader.readFile("grades.txt").getQuestions();
        this.stage = stage;

        this.drawScene(this.buildQuestionsBody(this.questions.get(this.currentPos)));
    }

    private void drawScene(FlowPane questionBody) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane rootPane = new Pane();

        rootPane.getChildren().addAll(canvas, questionBody, this.buildControlsBody());
        Scene scene = new Scene(rootPane);
        this.stage.setScene(scene);
        this.stage.setTitle("Multiple Choice Dude");
        this.stage.show();
    }

    private FlowPane buildQuestionsBody(MultipleChoiceModel question) {
        FlowPane answersContainer = new FlowPane();
        FlowPane labelContainer = new FlowPane();

        Label label = new Label();
        label.setText((this.currentPos + 1) + ". " + question.title);
        labelContainer.getChildren().add(label);
        answersContainer.getChildren().add(labelContainer);

        for (int i = 0; i < question.answers.size(); i++) {
            AnswerModel mcm = question.answers.get(i);
            MultipleChoiceCheckBox checkBox = new MultipleChoiceCheckBox(mcm.key, mcm.correct);
            answersContainer.getChildren().add(checkBox);
        }

        return answersContainer;
    }

    private FlowPane buildSolutionsBody(MultipleChoiceModel question) {
        FlowPane solutionsContainer = new FlowPane();
        FlowPane labelContainer = new FlowPane();

        Label label = new Label();
        label.setText((this.currentPos + 1) + ". " + question.title);
        labelContainer.getChildren().add(label);
        solutionsContainer.getChildren().add(labelContainer);

        for (int i = 0; i < question.answers.size(); i++) {
            AnswerModel mcm = question.answers.get(i);
            MultipleChoiceCheckBox checkBox = new MultipleChoiceCheckBox(mcm.key, mcm.correct);
            if (checkBox.correct == true) {
                checkBox.setStyle("-fx-background-color: #00FF00;");
            }
            solutionsContainer.getChildren().add(checkBox);
        }

        return solutionsContainer;
    }

    private FlowPane buildControlsBody() {
        FlowPane controlsContainer = new FlowPane();
        controlsContainer.setLayoutY(HEIGHT);

        Button previousButton = new Button("Previous");
        previousButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                goPrevious();
            }
        });

        Button nextButton = new Button("Next");
        nextButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                goNext();
            }
        });

        Button checkAnswersButton = new Button("Check");
        checkAnswersButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                checkAnswers();
            }
        });

        controlsContainer.getChildren().addAll(previousButton, checkAnswersButton, nextButton);
        return controlsContainer;
    }

    private void checkAnswers() {
        this.drawScene(buildSolutionsBody(this.questions.get(this.currentPos)));
    }

    private void goNext() {
        if (this.currentPos == this.questions.size() - 1) {
            this.currentPos = 0;
        } else {
            this.currentPos++;
        }
        this.drawScene(buildQuestionsBody(this.questions.get(this.currentPos)));
    }

    private void goPrevious() {
        if (this.currentPos > 0) {
            this.currentPos--;
            this.drawScene(buildQuestionsBody(this.questions.get(this.currentPos)));
        }
    }
}
