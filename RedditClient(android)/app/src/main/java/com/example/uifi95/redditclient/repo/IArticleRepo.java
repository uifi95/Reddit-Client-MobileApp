package com.example.uifi95.redditclient.repo;

import com.example.uifi95.redditclient.model.Article;

import java.util.List;

/**
 * Created by uifi95 on 11.12.2016.
 */

public interface IArticleRepo {
    Article createArticle(Article article);
    void removeArticle(int id);
    Article updateArticle(Article article);
    Article getArticle(int id);
    List<Article> getAllArticles();
}
