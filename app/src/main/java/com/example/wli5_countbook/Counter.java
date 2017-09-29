package com.example.wli5_countbook;

/**
 * Created by ${WeiLi5} on ${12}.
 */

public class Counter {
    private String name;
    private String date;
    private int initialValue;
    private int currentValue;
    private String comment;

    public Counter(String name, int initialValue) {
        this.name = name;

        this.initialValue = initialValue;
        this.currentValue = initialValue;
        this.date = date;
    }

    public Counter(String name, int value, String comment) {
        this.name = name;

        this.initialValue = value;
        this.currentValue = value;
        this.comment=comment;
        this.date = date;
    }

    //unfinish
    public void setDate() {
        this.date = date;
    }

    public void incrementCount() {
        this.currentValue = this.currentValue + 1;
        setDate();
    }

    public void decrementCount() {
        this.currentValue = this.currentValue - 1;
        setDate();
    }

    public void resetCount() {
        this.currentValue = this.initialValue;
        setDate();
    }

    public void setComment(String newComment){
        this.comment=newComment;
    }


}