package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;


/**
 * Created by dengnan on 16/2/29.
 */
public class BibliotecaHandlerTest {

    @Test
    public void testGenerateTableData(){
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("Hello World","DN","1991"));
        bookShelf.addBook(new Book("Love, pray and eat","J","2000"));

        Object[][] result = BibliotecaHandler.generateTableData(bookShelf.getBookList());
        assertEquals("Hello World",result[0][0]);
        assertEquals("DN",result[0][1]);
        assertEquals("1991",result[0][2]);
        assertEquals("Love, pray and eat",result[1][0]);
        assertEquals("J",result[1][1]);
        assertEquals("2000",result[1][2]);
    }

    @Test
    public void testSearchBook(){
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(new Book("Hello World","DN","1991"));
        bookShelf.addBook(new Book("Love, pray and eat","J","2000"));
        assertSame(BibliotecaHandler.searchBook("Hello World",bookShelf),true);
    }

    @Test
    public void testGetTargetBookByName(){
        Book targetBook = new Book("Hello World","DN","1991");
        ArrayList<Book> targetBookList = new ArrayList<Book>();
        targetBookList.add(targetBook);
        assertEquals(targetBookList,BibliotecaHandler.getTargetBook(targetBook));
    }
}
