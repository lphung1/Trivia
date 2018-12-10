package com.example.loi.trivia;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;

public class Question implements Serializable {

    String question, text, answer, category  = null;
    JSONArray choices;

    //blank constructor
    public Question(){

    }

    //sets question and replaces html quotes into string quotes
    public void setQuestion(String s){
        s = s.replaceAll("&quot;","'");
        s = s.replaceAll("&#039;","'");
        s = s.replaceAll("&eacute;", "");
        s = s.replaceAll("&amp;","&");


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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getChoices(int i) {

        try {
            return choices.get(i).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

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
