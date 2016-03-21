package com.example.user.brisbin_test2;

/**
 * Created by User on 12/9/2015.
 */
public class UserInput {
    private long id;
    private String inputText;
    private long dayOfWeek;

    public UserInput(long id, String inputText, long dayOfWeek){
        this.id = id;
        this.inputText = inputText;
        this.dayOfWeek = dayOfWeek;
    }

    public UserInput(String inputText, long dayOfWeek){
        this.inputText = inputText;
        this.dayOfWeek = dayOfWeek;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public long getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(long dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
