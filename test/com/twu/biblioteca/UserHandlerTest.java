package com.twu.biblioteca;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by dengnan on 16/3/5.
 */
public class UserHandlerTest {
    @Test
    public void testGetUserList(){
        ArrayList<User> userList = UserHandler.getUserList();
        assertEquals("dengnan",userList.get(0).getUserName());
        assertEquals("Gary",userList.get(1).getUserName());
    }

    @Test
    public void testSearchUser(){
        ArrayList<User> userList = new ArrayList<User>();
        userList.add(new User("123-4567", "123456", "dengnan","nan1108rm@outlook.com","12345678901"));
        userList.add(new User("123-6253", "54321", "Gary","gary@outlook.com","12345678901"));
        ArrayList<User> targetList = UserHandler.searchUser("123-4567",userList);
        assertEquals("dengnan", targetList.get(0).getUserName());
    }
}
