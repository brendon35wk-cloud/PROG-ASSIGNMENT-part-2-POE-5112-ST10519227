package com.mycompany.logintest;

import org.junit.Test;
import static org.junit.Assert.*;

public class LoginTest {

    @Test
    public void testUsernameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.registerUser();
        assertEquals("Welcome Kyle,Smith it is great to see you.", result);
    }

    @Test
    public void testUsernameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.registerUser();
        assertEquals("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.", result);
    }

    @Test
    public void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.capturePassword("Ch&&sec@ke99!");
        assertEquals("Password successfully captured.", result);
    }

    @Test
    public void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        String result = login.capturePassword("password");
        assertEquals("Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.", result);
    }

    @Test
    public void testCellNumberCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.captureCellPhoneNumber("+27838968976");
        assertEquals("Cell number successfully captured.", result);
    }

    @Test
    public void testCellNumberIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        String result = login.captureCellPhoneNumber("08966553");
        assertEquals("Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.", result);
    }

    @Test
    public void testLoginSuccessful() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.loginUser("kyl_1", "Ch&&sec@ke99!"));
    }

    @Test
    public void testLoginFailed() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.loginUser("kyl_1", "wrongpassword"));
    }

    @Test
    public void testUsernameCorrectlyFormattedTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkUserName("kyl_1"));
    }

    @Test
    public void testUsernameIncorrectlyFormattedFalse() {
        Login login = new Login("kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.checkUserName("kyle!!!!!!!"));
    }

    @Test
    public void testPasswordMeetsComplexityTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testPasswordDoesNotMeetComplexityFalse() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertFalse(login.checkPasswordComplexity("password"));
    }

    @Test
    public void testCellNumberCorrectlyFormattedTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkCellPhoneNumber("+27838968976"));
    }

    @Test
    public void testCellNumberIncorrectlyFormattedFalse() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        assertFalse(login.checkCellPhoneNumber("08966553"));
    }
}