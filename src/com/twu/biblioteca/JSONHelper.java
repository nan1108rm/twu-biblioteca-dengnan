package com.twu.biblioteca;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dengnan on 16/3/4.
 */
public class JSONHelper {
    private static BufferedReader reader;
    private static BufferedWriter writer;

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

    public static JSONObject createJSONObjectFromList(ArrayList<Book> bookList){
        ArrayList<Map> list = new ArrayList<Map>();
        JSONObject jsonObject = new JSONObject();
        for(int i=0; i<bookList.size(); i++){
            Map<String, String> map = new HashMap<String, String>();
            map.put("year", bookList.get(i).getYear());
            map.put("name", bookList.get(i).getName());
            map.put("author", bookList.get(i).getAuthor());

            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        jsonObject.put("books",jsonArray);
        return jsonObject;
    }

    public static void writeJSON(JSONObject jsonObject, String filePath) throws IOException {
        File f = new File(filePath);
        if(!f.exists()){
            try{
                f.createNewFile();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        writer = new BufferedWriter(new FileWriter(filePath,false));
        writer.write(jsonObject.toString());
        writer.close();
    }
}
