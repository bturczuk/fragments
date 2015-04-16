package com.example.beataturczuk.fragments.DataBase.dbObjects;

/**
 * Created by beataturczuk on 13.04.15.
 */
public class Quote {
    private int id;
    private String author;
    private String body;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
