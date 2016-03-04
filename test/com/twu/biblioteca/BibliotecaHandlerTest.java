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
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Hello World", "DN", "1991"));
        bookList.add(new Book("Eat pray love", "Julie", "1998"));
        Object[][] bookArray = BibliotecaHandler.generateTableData(bookList);
        assertEquals("Hello World", bookArray[0][0]);
        assertEquals("Eat pray love", bookArray[1][0]);
        assertEquals("1998", bookArray[1][2]);
    }

    @Test
    public void testSearchBook(){
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Hello World", "DN", "1991"));
        bookList.add(new Book("Eat pray love", "Julie", "1998"));
        ArrayList<Book> targetList = BibliotecaHandler.searchBook("Hello World",bookList);
        assertEquals("Hello World", targetList.get(0).getName());
    }

    @Test
    public void testCheckoutBook(){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/CurrentBookList.json";
        String expectedStr = "{\"books\":[{\"year\":\"1998\",\"author\":\"Julie\",\"name\":\"Eat pray love\"}]}";
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Hello World", "DN", "1991"));
        bookList.add(new Book("Eat pray love", "Julie", "1998"));
        ArrayList<Book> removedBook = new ArrayList<Book>();
        removedBook.add(new Book("Hello World","DN","1991"));
        BibliotecaHandler.checkOutBook(removedBook);
        String actualStr = JSONHelper.readJSON(filePath);
        assertEquals(expectedStr,actualStr);
    }
}
