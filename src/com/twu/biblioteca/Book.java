package com.twu.biblioteca;

/**
 * Created by dengnan on 16/2/29.
 */
public class Book {
    private String name;
    private String author;
    private String publishedYear;

    public Book(String name, String author, String year){
        this.name = name;
        this.author = author;
        this.publishedYear = year;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getAuthor(){
        return author;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getYear(){
        return publishedYear;
    }

    public void setYear(String year){
        this.publishedYear = year;
    }
}
