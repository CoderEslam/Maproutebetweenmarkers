package com.doubleclick.maproutebetweenmarkers;

import com.google.gson.annotations.SerializedName;

/**
 * Created By Eslam Ghazy on 1/15/2023
 */
public class Duration {
    @SerializedName("text")
    private String text;

    @SerializedName("value")
    private int value;

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public String toString() {
        return "Duration{" +
                "text='" + text + '\'' +
                ", value=" + value +
                '}';
    }
}
