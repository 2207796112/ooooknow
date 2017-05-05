package com.ooooknow.flove.byxy.ooooknow.model;

import java.util.List;

/**
 * Created by FLOVE on 2017/5/1.
 * 知乎新闻评论
 */

public class NewsComments {

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        /**
         * author : 闷不会玩围脖
         * content : 要用高难度高技术骗的人，他有非常高的概率在你提出要钱的那一刻发现你是个骗子，那不前功尽弃吗？太划不来了。还不如用非常弱智的骗术骗钱，这么拙劣都看不出来简直是财神送钱呐。
         * avatar : http://pic4.zhimg.com/01d9e29ae2ff6c4f973a5a7c7b93a73b_im.jpg
         * time : 1493763864
         * reply_to : {"content":"有没有可能骗子看了这篇文章，开发出新的套路","status":0,"id":28848123,"author":"许彦祖"}
         * id : 28881699
         * likes : 0
         */

        private String author;
        private String content;
        private String avatar;
        private int time;
        private ReplyToBean reply_to;
        private int id;
        private int likes;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        public ReplyToBean getReply_to() {
            return reply_to;
        }

        public void setReply_to(ReplyToBean reply_to) {
            this.reply_to = reply_to;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public static class ReplyToBean {
            /**
             * content : 有没有可能骗子看了这篇文章，开发出新的套路
             * status : 0
             * id : 28848123
             * author : 许彦祖
             */

            private String content;
            private int status;
            private int id;
            private String author;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAuthor() {
                return author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }
        }
    }
}
