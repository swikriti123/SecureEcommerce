package com.example.ecosmetics;

import com.example.ecosmetics.bll.SignUpBLL;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

public class SignUpTest {
    @Test
    public void SignUpTest() throws IOException {
        SignUpBLL registerUser=new SignUpBLL(
                "sadina",
                "magar",
                "itahari",
                "sadinajmagar1999@gmail.com",
                "9803933811",
                "sadina1266",
                "sadina123"
        );
        boolean result=registerUser.register();
        assertEquals(true,result);
    }
    @Test
    public void emailValidation_isCorrect() {
        Assert.assertEquals(false, Utilities.isValidEmail("example"));
        Assert.assertEquals(false, Utilities.isValidEmail("example@gmail"));
        Assert.assertEquals(true, Utilities.isValidEmail("example@gmail.com"));
        Assert.assertEquals(false, Utilities.isValidEmail("example@gmail@.com"));
        Assert.assertEquals(true, Utilities.isValidEmail("example.next@gmail.com"));
        Assert.assertEquals(false, Utilities.isValidEmail(""));
    }


}
