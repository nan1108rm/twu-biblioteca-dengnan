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
        ArrayList<Book> itemList = new ArrayList<Book>();
        JSONObject json= JSONObject.fromObject(bookStr);
        JSONArray jsonArray=json.getJSONArray("items");
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject element = JSONObject.fromObject(jsonArray.get(i));
            Book aItem = new Book(element.getString("name"), element.getString("author"), element.getString("year"));
            itemList.add(aItem);
        }
        return itemList;
    }

    public static ArrayList<Movie> createMovieArrayFromJSON(String itemStr){
        ArrayList<Movie> itemList = new ArrayList<Movie>();
        JSONObject json= JSONObject.fromObject(itemStr);
        JSONArray jsonArray=json.getJSONArray("items");
        for(int i=0; i<jsonArray.size(); i++){
            JSONObject element = JSONObject.fromObject(jsonArray.get(i));
            Movie aItem = new Movie(element.getString("name"), element.getString("director"), element.getString("year"), element.getString("rating"));
            itemList.add(aItem);
        }
        return itemList;
    }

    public static JSONObject createJSONObjectFromBookList(ArrayList<Book> itemList){
        ArrayList<Map> list = new ArrayList<Map>();
        JSONObject jsonObject = new JSONObject();
        for(int i=0; i<itemList.size(); i++){
            Book aBook = itemList.get(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", aBook.getName());
            map.put("author", aBook.getAuthor());
            map.put("year", aBook.getYear());
            list.add(map);
        }

        JSONArray jsonArray = JSONArray.fromObject(list);
        jsonObject.put("items",jsonArray);
        return jsonObject;
    }

    public static JSONObject createJSONObjectFromMovieList(ArrayList<Movie> itemList){
        ArrayList<Map> list = new ArrayList<Map>();
        JSONObject jsonObject = new JSONObject();
        for(int i=0; i<itemList.size(); i++){
            Movie aMovie = itemList.get(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put("name", aMovie.getName());
            map.put("director", aMovie.getDirector());
            map.put("year", aMovie.getYear());
            map.put("rating", aMovie.getRating());
            list.add(map);
        }
        JSONArray jsonArray = JSONArray.fromObject(list);
        jsonObject.put("items",jsonArray);
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
