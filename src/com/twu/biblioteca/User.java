package com.twu.biblioteca;

/**
 * Created by dengnan on 16/3/5.
 */
public class User {
    private String libraryNum;
    private String password;
    private String name;
    private String email;
    private String phone;

    public User(String libraryNum, String password, String name, String email, String phone){
        this.libraryNum = libraryNum;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getLibraryNum(){
        return libraryNum;
    }
    public String getPassword(){
        return password;
    }
    public String getUserName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getPhone(){
        return phone;
    }
}
