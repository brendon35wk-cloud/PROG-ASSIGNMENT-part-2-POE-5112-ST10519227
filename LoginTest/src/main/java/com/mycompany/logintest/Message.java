/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.logintest;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author Student
 */

public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageText;
    private String messageHash;

    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> storedMessages = new ArrayList<>();
    private static int totalMessages = 0;

    public Message(String messageID, int messageNumber, String recipient, String messageText) {
        this.messageID = messageID;
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageText = messageText;
        this.messageHash = createMessageHash();
    }

    // makes a random ten digit message ID
    public static String generateMessageID() {
        Random rand = new Random();
        String id = "";
        for (int i = 0; i < 10; i++) {
            id = id + rand.nextInt(10);
        }
        return id;
    }

    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public static String checkRecipientCell(String cell) {
        if (cell.startsWith("+27") && cell.length() == 12) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
    }

    public static String checkMessageLength(String text) {
        if (text.length() <= 250) {
            return "Message ready to send.";
        }
        int extra = text.length() - 250;
        return "Message exceeds 250 characters by " + extra + "; please reduce the size.";
    }

    // removes things like commas and question marks from a word
    private static String cleanWord(String word) {
        String clean = "";
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                clean = clean + c;
            }
        }
        return clean;
    }

    // first two numbers of the ID : message number : first word + last word
    public String createMessageHash() {
        String firstTwo = messageID.substring(0, 2);
        String[] words = messageText.split(" ");
        String firstWord = cleanWord(words[0]);
        String lastWord = cleanWord(words[words.length - 1]);
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    // 1 = send, 2 = disregard, 3 = store
    public String sentMessage(int choice) {
        if (choice == 1) {
            sentMessages.add(getDetails());
            totalMessages++;
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else {
            storeMessage();
            return "Message successfully stored.";
        }
    }

    public String getDetails() {
        return "Message ID: " + messageID + "\n"
                + "Message Hash: " + messageHash + "\n"
                + "Recipient: " + recipient + "\n"
                + "Message: " + messageText + "\n";
    }

    public static String printMessages() {
        String all = "";
        for (int i = 0; i < sentMessages.size(); i++) {
            all = all + sentMessages.get(i) + "\n";
        }
        return all;
    }

    public static int returnTotalMessages() {
        return totalMessages;
    }

    // saves the stored messages in a JSON file
    // Reference: W3Schools (n.d.) Java Create and Write To Files. Available at: https://www.w3schools.com/java/java_files_create.asp
    // Reference: JSON.org (n.d.) Introducing JSON. Available at: https://www.json.org
    public void storeMessage() {
        String json = "{\"messageID\": \"" + messageID + "\", "
                + "\"messageHash\": \"" + messageHash + "\", "
                + "\"recipient\": \"" + recipient + "\", "
                + "\"message\": \"" + messageText.replace("\"", "\\\"") + "\"}";
        storedMessages.add(json);

        try {
            FileWriter writer = new FileWriter("messages.json");
            writer.write("[\n");
            for (int i = 0; i < storedMessages.size(); i++) {
                writer.write(storedMessages.get(i));
                if (i < storedMessages.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            writer.close();
        } catch (IOException e) {
            System.out.println("Could not save the message.");
        }
    }
}