package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by dengnan on 16/2/29.
 */
public class BookListTest {
    @Test
    public void testBookListAdd() {
        Book aBook = new Book("Hello World");
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(aBook);
        assertEquals(aBook, bookShelf.getBook(0));
        Book aBook2 = new Book("Love, pray and eat");
        bookShelf.addBook(aBook2);
        assertEquals(aBook2, bookShelf.getBook(1));
    }

    @Test
    public void testGetBookList() {
        Book aBook1 = new Book("Hello World");
        Book aBook2 = new Book("Love, pray and eat");
        ArrayList<Book> books = new ArrayList<Book>();
        books.add(aBook1);
        books.add(aBook2);
        BookShelf bookShelf = new BookShelf();
        bookShelf.addBook(aBook1);
        bookShelf.addBook(aBook2);
        assertTrue(books.equals(bookShelf.getBookList()));
    }
}