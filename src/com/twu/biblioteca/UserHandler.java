package com.twu.biblioteca;

import java.util.ArrayList;

/**
 * Created by dengnan on 16/3/5.
 */
public class UserHandler {
    static String path = System.getProperty("user.dir");
    static String userPath = path + "/src/com/twu/biblioteca/UserList.json";

    public static ArrayList<User> getUserList(){
        String userStr = JSONHelper.readJSON(userPath);
        return JSONHelper.createUserArrayFromJSON(userStr);
    }

    public static ArrayList<User> searchUser(String libraryNumber,ArrayList<User> userList){
        ArrayList<User> targetUserList = new ArrayList<User>();
        for(User user:userList){
            if(user.getLibraryNum().equals(libraryNumber)){
                targetUserList.add(user);
            }
        }
        return targetUserList;
    }
}
