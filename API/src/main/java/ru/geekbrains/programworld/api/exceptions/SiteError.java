package ru.geekbrains.programworld.api.exceptions;


import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class SiteError {
    private List<String> messages;
    private Date date;

    public SiteError() {
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SiteError(List<String> messages) {
        this.messages = messages;
        this.date = new Date();
    }

    public SiteError(String message) {
        this(List.of(message));
    }

    public SiteError(String... messages) {
        this(Arrays.asList(messages));
    }

}
