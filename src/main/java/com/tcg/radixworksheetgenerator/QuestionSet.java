package com.tcg.radixworksheetgenerator;


import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;

import java.util.*;
import java.util.function.Consumer;

public class QuestionSet implements Iterable<Question> {

    private List<Question> questions;
    public final String direction;
    public final int lastNumber;

    public QuestionSet(int firstNumber, Base target, int numPerBasePerDifficulty) {
        this.direction = "Convert the following numbers to " + target.toString().toLowerCase() + ".";
        questions = new ArrayList<>();
        int questionNumber = firstNumber;
        for(Difficulty difficulty : Difficulty.values()) {
            for (Base given : Base.values()) {
                if (given != target) {
                    for (int i = 0; i < numPerBasePerDifficulty; i++) {
                        addQuestion(questionNumber, target, given, difficulty);
                        questionNumber++;
                    }
                }
            }
        }
        questions.sort(Comparator.comparingInt(o -> o.questionNumber));
        lastNumber = questions.get(questions.size() - 1).questionNumber;
    }

    public void printQuestions() {
        System.out.println(direction);
        for (Question question : questions) {
            System.out.println(question.question());
        }
    }

    public void printAnswers() {
        for (Question question : questions) {
            System.out.println(question.answer());
        }
    }

    private void addQuestion(int questionNumber, Base target, Base given, Difficulty difficulty) {
        Question question;
        do {
            question = new Question(questionNumber, given, target, difficulty);
        } while (questions.contains(question));
        questions.add(question);
    }

    @Override
    public Iterator<Question> iterator() {
        return questions.iterator();
    }

    @Override
    public void forEach(Consumer<? super Question> action) {
        questions.forEach(action);
    }

    @Override
    public Spliterator<Question> spliterator() {
        return questions.spliterator();
    }
}
