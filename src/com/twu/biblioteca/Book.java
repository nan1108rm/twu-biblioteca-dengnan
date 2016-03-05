package com.twu.biblioteca;

/**
 * Created by dengnan on 16/2/29.
 */
public class Book extends Item{
    private String author;

    public Book(String name, String author, String year){
        this.name = name;
        this.author = author;
        this.publishedYear = year;
    }

    public String getAuthor(){
        return author;
    }

}
