package com.twu.biblioteca;

/**
 * Created by dengnan on 16/3/5.
 */
public class Movie extends Item{

    private String director;
    private String rating;

    public Movie(String name, String director, String year, String rating){
        this.name = name;
        this.director = director;
        this.publishedYear = year;
        this.rating = rating;
    }

    public String getDirector(){
        return director;
    }

    public String getRating() {
        return rating;
    }
}
