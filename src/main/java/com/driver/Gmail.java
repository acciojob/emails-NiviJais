package com.driver;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    private ArrayList<Mail>  Inbox;
    private ArrayList<Mail>  Trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        Inbox = new ArrayList<Mail>();
        Trash = new ArrayList<Mail>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(Inbox.size() >= inboxCapacity){
            moveOldestMailToTrash();
        }

        Inbox.add(new Mail(date, sender, message));

       // Ensure the inbox is sorted based on date after adding a new mail
//        Inbox.sort(Comparator.comparing(Mail::getDate));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        int mailToRemove = findMailByMessage(message);

        for (Mail mail : Inbox) {
            if (mail.getMessage().equals(message)) {
                if(mailToRemove > 1){
                    Inbox.remove(mail);
                    Trash.add(mail);
                }else{
                    break;
                }
            }
        }
    }

    private int findMailByMessage(String message) {
        int cnt = 0;
        for (Mail mail : Inbox) {
            if (mail.getMessage().equals(message)) {
                cnt++;
            }
        }
        return cnt;
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(Inbox.isEmpty()){
            return null;
        }

        return Inbox.get(Inbox.size() - 1).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(Inbox.isEmpty()){
            return null;
        }

        return Inbox.get(0).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int count = 0;

        for (Mail mail : Inbox) {
            Date mailDate = mail.getDate();
            if (mailDate.equals(start) || (mailDate.after(start) && mailDate.before(end)) || mailDate.equals(end)) {
                count++;
            }
        }

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return Inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return Trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        Trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }

    private void moveOldestMailToTrash() {
        // Move the oldest mail in the inbox to trash
        Mail oldestMail = Inbox.remove(0);
        Trash.add(oldestMail);
    }

    private static class Mail{
        private Date date;
        private String senderId;
        private String message;

        public Mail(Date date, String senderId, String message) {
            this.date = date;
            this.senderId = senderId;
            this.message = message;
        }

        public Date getDate() {
            return date;
        }

        public String getSender() {
            return senderId;
        }

        public String getMessage() {
            return message;
        }
    }
}
