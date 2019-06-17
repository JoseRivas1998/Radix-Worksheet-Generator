package com.tcg.radixworksheetgenerator;

import org.json.JSONObject;

import java.util.Objects;

public class Question implements JSONAble{

    public final int questionNumber;
    public final int answer;
    public final Base givenRadix;
    public final Base targetRadix;

    public Question(int questionNumber, Base givenRadix, Base targetRadix, Difficulty difficulty) {
        this.questionNumber = questionNumber;
        this.answer = Util.random(difficulty.LOWEST_NUMBER, difficulty.HIGHEST_NUMBER);
        this.givenRadix = givenRadix;
        this.targetRadix = targetRadix;
    }

    public String question() {
        return questionNumber + ") " + Integer.toString(answer, givenRadix.radix);
    }

    public String answer() {
        return questionNumber + ") " + Integer.toString(answer, targetRadix.radix);
    }

    @Override
    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("questionNumber", questionNumber);
        jsonObject.put("answer", answer);
        jsonObject.put("givenRadix", givenRadix.radix);
        jsonObject.put("targetRadix", targetRadix);
        return jsonObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return answer == question.answer &&
                givenRadix == question.givenRadix &&
                targetRadix == question.targetRadix;
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer, givenRadix, targetRadix);
    }
}
