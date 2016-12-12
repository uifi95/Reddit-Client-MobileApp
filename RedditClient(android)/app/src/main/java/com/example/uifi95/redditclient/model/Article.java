package com.example.uifi95.redditclient.model;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by uifi95 on 11.12.2016.
 */

public class Article extends RealmObject {
    private int id;
    private String title;
    private String content;
    private Date postDate;
    private int rating;

    public Article(String title, String content, Date postDate, int rating) {
        this.title = title;
        this.content = content;
        this.postDate = postDate;
        this.rating = rating;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
