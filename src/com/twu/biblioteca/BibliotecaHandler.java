package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dengnan on 16/2/29.
 */
public class BibliotecaHandler {
    final static int BOOK_DETAIL_AMOUNT = 3;
    static BookShelf shelf;

    public static ArrayList<Book> initBookList(){
        shelf = new BookShelf();
        shelf.addBook(new Book("Hello World","DN","1991"));
        shelf.addBook(new Book("Love, pray and eat","J","2000"));
        return shelf.getBookList();
    }

    public static Object[][] generateTableData(ArrayList<Book> bookArraylist){
        Object[][] bookArray = new Object[bookArraylist.size()][BOOK_DETAIL_AMOUNT];
        for(int i=0; i<bookArraylist.size(); i++){
            Book aBook = bookArraylist.get(i);
            for(int j=0; j<BOOK_DETAIL_AMOUNT; j++){
                if(j == 0){
                    bookArray[i][j] = aBook.getName();
                }else if(j == 1){
                    bookArray[i][j] = aBook.getAuthor();
                }else{
                    bookArray[i][j] = aBook.getYear();
                }
            }
        }
        return bookArray;
    }

    public static ArrayList<Book> searchBook(String name,ArrayList<Book> bookList){
        ArrayList<Book> targetBookList = new ArrayList<Book>();
        for(Book aBook:bookList){
            if(aBook.getName().equals(name)){
                targetBookList.add(aBook);
            }
        }
        return targetBookList;
    }

    /*public static ArrayList<Book> returnBook(String name,ArrayList<Book> bookList){
        ArrayList<Book> targetList = searchBook(name, bookList);
        if(targetList.size() != 0){
            shelf.addBook(targetList.get(0));
        }
        return shelf.getBookList();
    }*/
}
