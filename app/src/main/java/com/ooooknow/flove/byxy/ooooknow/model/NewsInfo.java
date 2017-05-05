package com.ooooknow.flove.byxy.ooooknow.model;

/**
 * Created by FLOVE on 2017/4/29.
 * 新闻实体
 */

public class NewsInfo {
    private String newsID;
    private String newsTitle;
    private String newsContent;
    private String newsFirst;

    public NewsInfo(String news_ID,String news_title,String news_content,String news_first){
        this.newsID = news_ID;
        this.newsTitle = news_title;
        this.newsContent = news_content;
        this.newsFirst = news_first;
    }

    public NewsInfo(){

    }

    public String getNews_title() {
        return newsTitle;
    }

    public void setNews_title(String news_title) {
        this.newsTitle = news_title;
    }

    public String getNews_content() {
        return newsContent;
    }

    public void setNews_content(String news_content) {
        this.newsContent = news_content;
    }

    public String getNews_first() {
        return newsFirst;
    }

    public void setNews_first(String news_first) {
        this.newsFirst = news_first;
    }

    public String getNews_ID() {
        return newsID;
    }

    public void setNews_ID(String news_ID) {
        this.newsID = news_ID;
    }
}
