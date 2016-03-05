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
    public void testReadJSONItemList(){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/BookList.json";
        String json = "{\"items\":[{\"name\":\"Hello World\",\"author\":\"DN\",\"year\":\"1991\"},{\"name\":\"Eat pray love\",\"author\":\"Julie\",\"year\":\"1998\"}]}";
        String bookStr = JSONHelper.readJSON(filePath);
        assertEquals(json, bookStr);
    }

    @Test
    public void testCreateItemListFromJSON(){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/BookList.json";
        String itemStr = JSONHelper.readJSON(filePath);
        ArrayList<Book> itemList = JSONHelper.createBookArrayFromJSON(itemStr);
        assertEquals("Hello World", itemList.get(0).getName());
        assertEquals("Eat pray love", itemList.get(1).getName());
    }

    @Test
    public void testCreateJSONObjectFromBookList(){
        String expectedStr = "{\"items\":[{\"year\":\"1991\",\"author\":\"DN\",\"name\":\"Hello World\"}]}";
        ArrayList<Book> itemList = new ArrayList<Book>();
        itemList.add(new Book("Hello World","DN","1991"));
        String actualStr = JSONHelper.createJSONObjectFromBookList(itemList).toString();
        assertEquals(expectedStr,actualStr);
    }

    @Test
    public void testWriteJSONBookList() throws IOException {
        String expectedStr = "{\"items\":[{\"year\":\"1991\",\"author\":\"DN\",\"name\":\"Hello World\"}]}";
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/CurrentBookList.json";
        JSONObject jsonObj = JSONObject.fromObject(expectedStr);
        JSONHelper.writeJSON(jsonObj,filePath);
        String actualStr = JSONHelper.readJSON(filePath);
        assertEquals(expectedStr,actualStr);
    }
}
