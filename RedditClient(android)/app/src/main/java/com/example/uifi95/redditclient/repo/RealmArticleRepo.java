package com.example.uifi95.redditclient.repo;

import com.example.uifi95.redditclient.model.Article;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.internal.Context;

/**
 * Created by uifi95 on 12.12.2016.
 */

public class RealmArticleRepo implements IArticleRepo {

    private Realm realm;

    public RealmArticleRepo(Realm realm) {
        this.realm = realm;
    }

    @Override
    public Article createArticle(Article article) {
        List<Article> articles = getAllArticles();
        article.setId(articles.size() > 0 ? articles.get(articles.size() - 1).getId() + 1 : 0);
        realm.beginTransaction();
        realm.insert(article);
        realm.commitTransaction();
        return article;
    }

    @Override
    public void removeArticle(int id) {
        realm.beginTransaction();
        realm.where(Article.class).equalTo("id",id).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    @Override
    public Article updateArticle(Article article) {
        realm.beginTransaction();
        RealmResults<Article> rows = realm.where(Article.class).equalTo("id",article.getId()).findAll();
        for (Article a: rows){
            a.setTitle(article.getTitle());
            a.setContent(article.getContent());
            a.setPostDate(article.getPostDate());
            a.setRating(article.getRating());
        }
        realm.commitTransaction();
        return article;
    }

    @Override
    public Article getArticle(int id) {
        return realm.where(Article.class).equalTo("id",id).findFirst();
    }

    @Override
    public List<Article> getAllArticles() {
        return realm.where(Article.class).findAll();
    }
}
