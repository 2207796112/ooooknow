package com.ooooknow.flove.byxy.ooooknow.model;

/**
 * Created by FLOVE on 2017/4/30.
 * 回答
 */

public class AnswerInfo {
    private String answerUserHead;
    private String answerUserName;
    private String answerTime;
    private String answerTitle;
    private String answerFocus;
    private String answerAnswer;
    private String answerTag;

    public AnswerInfo(String answerUserhead, String answerUsername, String answerTime, String answerTitle, String answerFocus, String answerAnswer, String answerTag) {
        this.answerUserHead = answerUserhead;
        this.answerUserName = answerUsername;
        this.answerTime = answerTime;
        this.answerTitle = answerTitle;
        this.answerFocus = answerFocus;
        this.answerAnswer = answerAnswer;
        this.answerTag = answerTag;
    }

    public AnswerInfo(){

    }

    public String getAnswerTag() {
        return answerTag;
    }

    public void setAnswerTag(String answerTag) {
        this.answerTag = answerTag;
    }

    public String getAnswerAnswer() {
        return answerAnswer;
    }

    public void setAnswerAnswer(String answerAnswer) {
        this.answerAnswer = answerAnswer;
    }

    public String getAnswerFocus() {
        return answerFocus;
    }

    public void setAnswerFocus(String answerFocus) {
        this.answerFocus = answerFocus;
    }

    public String getAnswerTitle() {
        return answerTitle;
    }

    public void setAnswerTitle(String answerTitle) {
        this.answerTitle = answerTitle;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public String getAnswerUsername() {
        return answerUserName;
    }

    public void setAnswerUsername(String answerUsername) {
        this.answerUserName = answerUsername;
    }

    public String getAnswerUserhead() {
        return answerUserHead;
    }

    public void setAnswerUserhead(String answerUserhead) {
        this.answerUserHead = answerUserhead;
    }
}
