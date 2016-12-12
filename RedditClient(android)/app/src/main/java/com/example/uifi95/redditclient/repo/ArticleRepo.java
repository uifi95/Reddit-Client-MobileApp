package com.example.uifi95.redditclient.repo;

import com.example.uifi95.redditclient.model.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by uifi95 on 11.12.2016.
 */

public class ArticleRepo implements IArticleRepo {
    private List<Article> articles;
    private String filename;

    public ArticleRepo() {
       this.articles =  new ArrayList<>();
    }

    public ArticleRepo(String filename){
        this.filename = filename;
    }

    @Override
    public Article createArticle(Article article) {
        article.setId(articles.size());
        if (articles.add(article)){
            return articles.get(articles.size() - 1);
        }
        return null;
    }

    @Override
    public void removeArticle(int id) {
        articles.remove(id);
    }

    @Override
    public Article updateArticle(Article article) {
        articles.set(article.getId(), article);
        return articles.get(article.getId());
    }

    @Override
    public Article getArticle(int id) {
        return articles.get(id);
    }

    @Override
    public List<Article> getAllArticles() {
        return articles;
    }
}
