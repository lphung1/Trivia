package com.example.loi.trivia;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;

public class Question implements Serializable {

    String question, text, imageUrl, answer, category  = null;
    JSONArray choices;

    //blank constructor
    public Question(){

    }

    //sets question and replaces html quotes into string quotes
    public void setQuestion(String s){
        s = s.replaceAll("&quot;","'");
        s = s.replaceAll("&#039;","'");
        s = s.replaceAll("&eacute;", "");

        question = s;
    }

    public String getQuestion(){
        return question;
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

    public JSONArray getChoices() {
        return choices;
    }

    public void setChoices(JSONArray questions) {
        this.choices = questions;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
