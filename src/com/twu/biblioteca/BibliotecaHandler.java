package com.twu.biblioteca;

import net.sf.json.JSONObject;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by dengnan on 16/2/29.
 */
public class BibliotecaHandler {

    static String path = System.getProperty("user.dir");
    static String originBookPath = path + "/src/com/twu/biblioteca/BookList.json";
    static String originMoviePath = path + "/src/com/twu/biblioteca/MovieList.json";
    static String currentBookPath = path + "/src/com/twu/biblioteca/CurrentBookList.json";
    static String currentMoviePath = path + "/src/com/twu/biblioteca/CurrentMovieList.json";

    public static ArrayList<Book> getOriginBookList(){
        String bookStr = JSONHelper.readJSON(originBookPath);
        return JSONHelper.createBookArrayFromJSON(bookStr);
    }

    public static ArrayList<Movie> getOriginMovieList(){
        String movieStr = JSONHelper.readJSON(originMoviePath);
        return JSONHelper.createMovieArrayFromJSON(movieStr);
    }

    public static ArrayList<Book> getCurrentBookList(){
        File f = new File(currentBookPath);
        if(f.exists()){
            String bookStr = JSONHelper.readJSON(currentBookPath);
            return JSONHelper.createBookArrayFromJSON(bookStr);
        }else{
            return getOriginBookList();
        }
    }

    public static ArrayList<Movie> getCurrentMovieList(){
        File f = new File(currentMoviePath);
        if(f.exists()){
            String movieStr = JSONHelper.readJSON(currentMoviePath);
            return JSONHelper.createMovieArrayFromJSON(movieStr);
        }else{
            return getOriginMovieList();
        }
    }

    public static Object[][] generateTableDataForBook(ArrayList<Book> bookList){
        Object[][] itemArray = new Object[bookList.size()][3];
        for(int i=0; i<bookList.size(); i++){
            Book aBook= bookList.get(i);
            for(int j=0; j<3; j++){
                if(j == 0){
                    itemArray[i][j] = aBook.getName();
                }else if(j == 1){
                    itemArray[i][j] = aBook.getAuthor();
                }else{
                    itemArray[i][j] = aBook.getYear();
                }
            }
        }
        return itemArray;
    }

    public static Object[][] generateTableDataForMovie(ArrayList<Movie> movieList){
        Object[][] itemArray = new Object[movieList.size()][4];
        for(int i=0; i<movieList.size(); i++){
            Movie aMovie= movieList.get(i);
            for(int j=0; j<4; j++){
                if(j == 0){
                    itemArray[i][j] = aMovie.getName();
                }else if(j == 1){
                    itemArray[i][j] = aMovie.getDirector();
                }else if(j == 2){
                    itemArray[i][j] = aMovie.getYear();
                }else{
                    itemArray[i][j] = aMovie.getRating();
                }
            }
        }
        return itemArray;
    }

    public static ArrayList<Book> searchBook(String name,ArrayList<Book> bookList){
        ArrayList<Book> targetItemList = new ArrayList<Book>();
        for(Book aBook:bookList){
            if(aBook.getName().equals(name)){
                targetItemList.add(aBook);
            }
        }
        return targetItemList;
    }

    public static ArrayList<Movie> searchMovie(String name,ArrayList<Movie> movieList){
        ArrayList<Movie> targetItemList = new ArrayList<Movie>();
        for(Movie aMovie:movieList){
            if(aMovie.getName().equals(name)){
                targetItemList.add(aMovie);
            }
        }
        return targetItemList;
    }

    public static void checkOutBook(ArrayList<Book> bookList){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/CurrentBookList.json";
        if( bookList.size() >= 0){
            int i = 0;
            ArrayList<Book> removedBookList = getCurrentBookList();
            while(!bookList.get(0).getName().equals(removedBookList.get(i).getName())){
                i++;
            }
            removedBookList.remove(i);
            JSONObject jsonObject = JSONHelper.createJSONObjectFromBookList(removedBookList);
            try{
                JSONHelper.writeJSON(jsonObject,filePath);
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Thank you! Enjoy the book!");
        }else{
            JOptionPane.showMessageDialog(null,"That book is not avaliable!");
        }
    }

    public static void checkOutMovie(ArrayList<Movie> movieList){
        String path = System.getProperty("user.dir");
        String filePath = path + "/src/com/twu/biblioteca/CurrentMovieList.json";
        if( movieList.size() >= 0){
            int i = 0;
            ArrayList<Movie> removedMovieList = getCurrentMovieList();
            while(!movieList.get(0).getName().equals(removedMovieList.get(i).getName())){
                i++;
            }
            removedMovieList.remove(i);
            JSONObject jsonObject = JSONHelper.createJSONObjectFromMovieList(removedMovieList);
            try{
                JSONHelper.writeJSON(jsonObject,filePath);
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Thank you! Enjoy the Movie!");
        }else{
            JOptionPane.showMessageDialog(null,"That movie is not avaliable!");
        }
    }

    public static void returnBook(ArrayList<Book> bookList){
        if( bookList.size() >= 0){
            ArrayList<Book> addedBookList = getCurrentBookList();
            addedBookList.add(bookList.get(0));
            JSONObject jsonObject = JSONHelper.createJSONObjectFromBookList(addedBookList);
            try{
                JSONHelper.writeJSON(jsonObject,currentBookPath);
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Thank you for returning the book!");
        }else{
            JOptionPane.showMessageDialog(null,"That is not a valid book to return!");
        }
    }

    public static void returnMovie(ArrayList<Movie> movieList){
        if( movieList.size() >= 0){
            ArrayList<Movie> addedMovieList = getCurrentMovieList();
            addedMovieList.add(movieList.get(0));
            JSONObject jsonObject = JSONHelper.createJSONObjectFromMovieList(addedMovieList);
            try{
                JSONHelper.writeJSON(jsonObject,currentMoviePath);
            }catch(Exception e){
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"Thank you for returning the movie!");
        }else{
            JOptionPane.showMessageDialog(null,"That is not a valid movie to return!");
        }
    }
}
