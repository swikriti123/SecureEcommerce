package com.example.ecosmetics;

import com.example.ecosmetics.Model.User;
import com.example.ecosmetics.bll.LoginBLL;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginTest {
    @Test
    public void testLogin() {
        LoginBLL loginBLL = new LoginBLL("sadina123", "sadina123");
        boolean result = loginBLL.checkUser();
        assertEquals(true, result);
    }
}


