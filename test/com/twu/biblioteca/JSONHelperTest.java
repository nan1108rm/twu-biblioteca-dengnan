package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by dengnan on 16/3/4.
 */
public class JSONHelperTest {
    @Test
    public void testReadJSONBookList(){
        String filePath = "/Users/jiboyu/Desktop/TW/101/TWU48th-Nan/TWUBiblioteca/TWU_Biblioteca-master/src/com/twu/biblioteca/BookList.json";
        String json = "{\"books\":[{\"name\":\"Hello World\",\"author\":\"DN\",\"year\":\"1991\"},{\"name\":\"Eat pray love\",\"author\":\"Julie\",\"year\":\"1998\"}]}";
        String bookStr = JSONHelper.readJSON(filePath);
        assertEquals(json, bookStr);
    }

    @Test
    public void testCreateBookListFromJSON(){
        String filePath = "/Users/jiboyu/Desktop/TW/101/TWU48th-Nan/TWUBiblioteca/TWU_Biblioteca-master/src/com/twu/biblioteca/BookList.json";
        String bookStr = JSONHelper.readJSON(filePath);
        ArrayList<Book> bookList = JSONHelper.createBookArrayFromJSON(bookStr);
        assertEquals("Hello World", bookList.get(0).getName());
        assertEquals("Eat pray love", bookList.get(1).getName());
    }
}
