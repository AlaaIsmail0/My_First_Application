package com.example.myfirstapplication;

public class Book {
    private String title;
    private String auther;
    private String catagory;

    public Book(String title, String auther, String catagory) {
        this.title = title;
        this.auther = auther;
        this.catagory = catagory;
    }

    public String getTitle() {
        return title;
    }

    public String getAuther() {
        return auther;
    }

    public String getCatagory() {
        return catagory;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public void setCatagory(String catagory) {
        this.catagory = catagory;
    }
}
