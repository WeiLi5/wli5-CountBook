package com.example.wli5_countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        SimpleDateFormat simpleFormat= new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate=simpleFormat.format(new Date());
        this.date = formattedDate;
    }

    public Counter(String name, int value, String comment) {
        this.name = name;

        this.initialValue = value;
        this.currentValue = value;
        this.comment=comment;
        SimpleDateFormat simpleFormat= new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate=simpleFormat.format(new Date());
        this.date = formattedDate;
    }

    //https://stackoverflow.com/questions/12575990/calendar-date-to-yyyy-mm-dd-format-in-java
    public void setDate() {
        SimpleDateFormat simpleFormat= new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate=simpleFormat.format(new Date());
        this.date = formattedDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInitialValue(int initialValue){
        this.initialValue=initialValue;
        setDate();
    }

    public void setCurrentValue(int currentValue){
        this.currentValue=currentValue;
        setDate();
    }

    public void setComment(String comment){
        this.comment=comment;
    }

    public void incrementCount() {
        this.currentValue = this.currentValue + 1;
        setDate();
    }

    public void decrementCount() {
        if (currentValue >= 1)
            this.currentValue = this.currentValue - 1;
        setDate();
    }

    public void resetCount() {
        this.currentValue = this.initialValue;
        setDate();
    }


    public String getName() {return name;}

    public int getInitialValue() {return initialValue;}

    public int getCurrentValue() { return currentValue;}

    public String getDate() {return date;}

    public String getComment() {return comment;}


}