package com.example.loi.trivia;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;

public class Question implements Serializable {

    String id, text, imageUrl, answer = null;
    JSONArray questions;

    //blank constructor
    public Question(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public JSONArray getQuestions() {
        return questions;
    }

    public void setQuestions(JSONArray questions) {
        this.questions = questions;
    }
}
