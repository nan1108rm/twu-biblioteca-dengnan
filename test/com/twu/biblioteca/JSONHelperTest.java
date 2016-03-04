package com.twu.biblioteca;

import net.sf.json.JSONObject;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by dengnan on 16/3/4.
 */
public class JSONHelperTest {
    @Test
    public void testReadJSONBookList(){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/BookList.json";
        String json = "{\"books\":[{\"name\":\"Hello World\",\"author\":\"DN\",\"year\":\"1991\"},{\"name\":\"Eat pray love\",\"author\":\"Julie\",\"year\":\"1998\"}]}";
        String bookStr = JSONHelper.readJSON(filePath);
        assertEquals(json, bookStr);
    }

    @Test
    public void testCreateBookListFromJSON(){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/BookList.json";
        String bookStr = JSONHelper.readJSON(filePath);
        ArrayList<Book> bookList = JSONHelper.createBookArrayFromJSON(bookStr);
        assertEquals("Hello World", bookList.get(0).getName());
        assertEquals("Eat pray love", bookList.get(1).getName());
    }

    @Test
    public void testCreateJSONObjectFromList(){
        String expectedStr = "{\"books\":[{\"year\":\"1991\",\"author\":\"DN\",\"name\":\"Hello World\"}]}";
        ArrayList<Book> bookList = new ArrayList<Book>();
        bookList.add(new Book("Hello World","DN","1991"));
        String actualStr = JSONHelper.createJSONObjectFromList(bookList).toString();
        assertEquals(expectedStr,actualStr);
    }

    @Test
    public void testWriteJSONBookList() throws IOException {
        String expectedStr = "{\"books\":[{\"year\":\"1991\",\"author\":\"DN\",\"name\":\"Hello World\"}]}";
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/CurrentBookList.json";
        JSONObject jsonObj = JSONObject.fromObject(expectedStr);
        JSONHelper.writeJSON(jsonObj,filePath);
        String actualStr = JSONHelper.readJSON(filePath);
        assertEquals(expectedStr,actualStr);
    }
}
