/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author Student
 */
package com.mycompany.logintest;

import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageLengthSuccess() {
        String result = Message.checkMessageLength("Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message ready to send.", result);
    }

    @Test
    public void testMessageLengthFailure() {
        String longMessage = "";
        for (int i = 0; i < 255; i++) {
            longMessage = longMessage + "a";
        }
        String result = Message.checkMessageLength(longMessage);
        assertEquals("Message exceeds 250 characters by 5; please reduce the size.", result);
    }

    @Test
    public void testRecipientCorrect() {
        String result = Message.checkRecipientCell("+27718693002");
        assertEquals("Cell phone number successfully captured.", result);
    }

    @Test
    public void testRecipientIncorrect() {
        String result = Message.checkRecipientCell("08575975889");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", result);
    }

    @Test
    public void testMessageHashTestCase1() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("00:0:HITONIGHT", msg.createMessageHash());
    }

    @Test
    public void testMessageHashLoop() {
        String[] ids = {"0012345678", "5812345678"};
        String[] texts = {"Hi Mike, can you join us for dinner tonight?", "Hi Keegan, did you receive the payment?"};
        String[] expected = {"00:0:HITONIGHT", "58:1:HIPAYMENT"};

        for (int i = 0; i < ids.length; i++) {
            Message msg = new Message(ids[i], i, "+27718693002", texts[i]);
            assertEquals(expected[i], msg.createMessageHash());
        }
    }

    @Test
    public void testMessageIDCreated() {
        String id = Message.generateMessageID();
        Message msg = new Message(id, 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageSent() {
        Message msg = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        assertEquals("Message successfully sent.", msg.sentMessage(1));
    }

    @Test
    public void testMessageDisregarded() {
        Message msg = new Message("5812345678", 1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Press 0 to delete the message.", msg.sentMessage(2));
    }

    @Test
    public void testMessageStored() {
        Message msg = new Message("5812345678", 1, "08575975889", "Hi Keegan, did you receive the payment?");
        assertEquals("Message successfully stored.", msg.sentMessage(3));
    }

    @Test
    public void testTotalMessages() {
        int before = Message.returnTotalMessages();
        Message msg1 = new Message("0012345678", 0, "+27718693002", "Hi Mike, can you join us for dinner tonight?");
        Message msg2 = new Message("5812345678", 1, "08575975889", "Hi Keegan, did you receive the payment?");
        msg1.sentMessage(1);
        msg2.sentMessage(2);
        assertEquals(before + 1, Message.returnTotalMessages());
    }
}
