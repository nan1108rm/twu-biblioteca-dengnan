package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dengnan on 16/2/29.
 */
public class BookShelf {
    ArrayList<Book> bookList = new ArrayList<Book>();

    public void addBook(Book aBook){
        bookList.add(aBook);
    }

    public Book getBook(int position){
        return bookList.get(position);
    }

    public ArrayList<Book> getBookList(){
        return bookList;
    }

    public ArrayList<Book> removeBook(String name){
        for(Book aBook:bookList){
            if(aBook.getName().equals(name)){
                bookList.remove(aBook);
            }
        }
        return bookList;
    }
}
