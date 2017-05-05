package com.ooooknow.flove.byxy.ooooknow.model;

/**
 * Created by FLOVE on 2017/4/30.
 * 消息实体
 */

public class LetterInfo {
    private String letteName;
    private String letteHead;
    private String letteTime;
    private String letteMessage;


    public LetterInfo(String letteName, String letteHead, String letteTime, String letteMessage) {
        this.letteName = letteName;
        this.letteHead = letteHead;
        this.letteTime = letteTime;
        this.letteMessage = letteMessage;
    }

    public LetterInfo() {

    }

    public String getLetteName() {
        return letteName;
    }

    public void setLetteName(String letteName) {
        this.letteName = letteName;
    }

    public String getLetteHead() {
        return letteHead;
    }

    public void setLetteHead(String letteHead) {
        this.letteHead = letteHead;
    }

    public String getLetteTime() {
        return letteTime;
    }

    public void setLetteTime(String letteTime) {
        this.letteTime = letteTime;
    }

    public String getLetteMessage() {
        return letteMessage;
    }

    public void setLetteMessage(String letteMessage) {
        this.letteMessage = letteMessage;
    }
}
