package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static MultipleChoice readFile(String fileString) {
        MultipleChoice multipleChoice;
        File inputFile = new File(fileString);
        try {
            Scanner scanner = new Scanner(inputFile);
            List<String> questionData = new ArrayList<>();
            List<List<String>> questions = new ArrayList<>();

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();

                if (data.isEmpty()) {
                    questions.add(questionData);
                    questionData = new ArrayList<>();
                } else {
                    questionData.add(data);
                }
            }

            multipleChoice = new MultipleChoice(questions);
            scanner.close();
            return multipleChoice;
        } catch (FileNotFoundException e) {
            System.err.println("Scanning file " + fileString + "failed");
            return null;
        }
    }
}
