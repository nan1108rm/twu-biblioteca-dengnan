package com.twu.biblioteca;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by dengnan on 16/3/4.
 */
public class JSONHelper {
    private static BufferedReader reader;

    public static String readJSON(String filePath){
        String jsonStr = "";
        try{
            reader = new BufferedReader(new FileReader(filePath));
            String nextLine;
            while((nextLine = reader.readLine()) != null){
                jsonStr += nextLine;
            }
            reader.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return jsonStr;
    }

    public static ArrayList<Book> createBookArrayFromJSON(String bookStr){
        ArrayList<Book> bookList = new ArrayList<Book>();
        JSONObject json= JSONObject.fromObject(bookStr);
        JSONArray jsonArray=json.getJSONArray("books");
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject element = JSONObject.fromObject(jsonArray.get(i));
            Book aBook = new Book(element.getString("name"), element.getString("author"), element.getString("year"));
            bookList.add(aBook);
        }
        return bookList;
    }
}
