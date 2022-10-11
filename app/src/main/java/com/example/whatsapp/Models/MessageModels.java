package com.example.whatsapp.Models;

public class MessageModels {

    String uId, message,messageId;
    Long timeStamp;

    public MessageModels(String uId,String messageId, String message, Long timeStamp) {
        this.uId = uId;
        this.messageId = messageId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public MessageModels(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public MessageModels() {}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
