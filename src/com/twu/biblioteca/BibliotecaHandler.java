package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dengnan on 16/2/29.
 */
public class BibliotecaHandler {
    final static int BOOK_DETAIL_AMOUNT = 3;

    public static ArrayList<Book> initBookList(){
        BookShelf shelf = new BookShelf();
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
}
