package com.example.myapplication;

import android.graphics.Bitmap;

public class Person {
    private Bitmap photo;
    private String name;
    private String telNumber;
    private boolean IsChecked;


    public Person(Bitmap photo, String name, String telNumber, boolean isChecked) {
        this.photo = photo;
        this.name = name;
        this.telNumber = telNumber;
        this.IsChecked = isChecked;
    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public boolean isChecked() { return IsChecked; }
}
