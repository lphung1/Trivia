package com.example.loi.trivia;

import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONException;

/**
 * Class Holding a Question object. Question object is made up of a question, answer choices, the correct answer, and category.
 */
public class Question implements Serializable {

    String question, text, answer, category  = null;
    JSONArray choices;

    /**
     * Default constructor
     */
    public Question(){

    }

    /**
     * Sets question and removes any html based syntax
     * @param s - question to set for this question object
     */
    public void setQuestion(String s){
        s = s.replaceAll("&quot;","'");
        s = s.replaceAll("&#039;","'");
        s = s.replaceAll("&eacute;", "");
        s = s.replaceAll("&amp;","&");


        question = s;
    }

    /**
     * get question
     * @return String - current question
     */
    public String getQuestion(){
        return question;
    }

    /**
     * Get text
     * @return String - return text
     */
    public String getText() {
        return text;
    }

    /**
     * sets text
     * @param text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * returns string of answer
     * @return String - answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * sets answer
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * returns a string representation of one of the choices from a json array
     * @param i - index of choice in json array
     * @return - String choice
     */
    public String getChoices(int i) {

        try {
            return choices.get(i).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * sets choices as a Json Array
     * @param questions - name of choices
     */
    public void setChoices(JSONArray questions) {
        this.choices = questions;


    }

    /**
     * Gets string category
     * @return - String category
     */
    public String getCategory() {
        return category;
    }

    /**
     * sets category
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }
}
