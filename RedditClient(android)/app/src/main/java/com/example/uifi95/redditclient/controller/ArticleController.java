package com.example.uifi95.redditclient.controller;

import com.example.uifi95.redditclient.model.Article;
import com.example.uifi95.redditclient.repo.IArticleRepo;

import java.util.Date;
import java.util.List;

/**
 * Created by uifi95 on 11.12.2016.
 */

public class ArticleController {
    private IArticleRepo repo;

    public ArticleController(IArticleRepo repo) {
        this.repo = repo;
    }

    public Article addArticle(String title, String content, Date postDate, int rating){
        return repo.createArticle(new Article(title, content, postDate, rating));
    }

    public void removeArticle(int id){
        repo.removeArticle(id);
    }

    public Article updateArticle(int id, String title, String content, Date postDate, int rating){
        Article article = new Article(title, content, postDate, rating);
        article.setId(id);
        return repo.updateArticle(article);
    }

    public Article getArticle(int id){
        return repo.getArticle(id);
    }

    public List<Article> getAllArticles(){
        return repo.getAllArticles();
    }
}
