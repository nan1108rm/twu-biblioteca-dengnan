package com.twu.biblioteca;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

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
}