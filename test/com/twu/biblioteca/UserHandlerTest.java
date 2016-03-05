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
}
