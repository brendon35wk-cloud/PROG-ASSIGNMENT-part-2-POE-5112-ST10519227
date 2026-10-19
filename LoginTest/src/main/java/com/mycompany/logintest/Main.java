/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logintest;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first name: ");
        String firstName = sc.nextLine();

        System.out.print("Enter last name: ");
        String lastName = sc.nextLine();

        System.out.print("Enter username: ");
        String username = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        System.out.print("Enter cell phone number: ");
        String cellPhoneNumber = sc.nextLine();

        Login user = new Login(username, password, cellPhoneNumber, firstName, lastName);

        String regMessage = user.registerUser();
        System.out.println(regMessage);

        if (user.checkUserName(username) && user.checkPasswordComplexity(password) && user.checkCellPhoneNumber(cellPhoneNumber)) {
            System.out.println("\n--- Login ---");
            System.out.print("Enter username: ");
            String loginUsername = sc.nextLine();
            System.out.print("Enter password: ");
            String loginPassword = sc.nextLine();

            boolean isSuccess = user.loginUser(loginUsername, loginPassword);
            System.out.println(user.returnLoginStatus(isSuccess));
        }
    }
}