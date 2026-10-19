package com.mycompany.logintest;

public class Login {

    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String cellPhoneNumber, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhoneNumber = cellPhoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName(String username) {
        boolean hasUnderscore = false;
        for (int i = 0; i < username.length(); i++) {
            if (username.charAt(i) == '_') {
                hasUnderscore = true;
            }
        }
        if (hasUnderscore && username.length() <= 5) {
            return true;
        }
        return false;
    }

    public boolean checkPasswordComplexity(String password) {
        boolean hasCapital = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isUpperCase(c)) {
                hasCapital = true;
            }
            if (Character.isDigit(c)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(c)) {
                hasSpecial = true;
            }
        }
        if (password.length() >= 8 && hasCapital && hasNumber && hasSpecial) {
            return true;
        }
        return false;
    }

    public boolean checkCellPhoneNumber(String cellPhoneNumber) {
        if (cellPhoneNumber.startsWith("+27") && cellPhoneNumber.length() == 12) {
            return true;
        }
        return false;
    }

    public String captureUsername(String username) {
        if (checkUserName(username)) {
            return "Username successfully captured.";
        }
        return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
    }

    public String capturePassword(String password) {
        if (checkPasswordComplexity(password)) {
            return "Password successfully captured.";
        }
        return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
    }

    public String captureCellPhoneNumber(String cellPhoneNumber) {
        if (checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number successfully captured.";
        }
        return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
    }

    public String registerUser() {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhoneNumber)) {
            return "Cell number is incorrectly formatted or does not contain an international code; please correct the number and try again.";
        }
        return "Welcome " + firstName + "," + lastName + " it is great to see you.";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            return true;
        }
        return false;
    }

    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + "," + lastName + " it is great to see you.";
        }
        return "Username or password incorrect, please try again.";
    }
}