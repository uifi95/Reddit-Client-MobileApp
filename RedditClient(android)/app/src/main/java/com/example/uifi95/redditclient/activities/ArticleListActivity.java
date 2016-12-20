package com.example.uifi95.redditclient.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.uifi95.redditclient.R;
import com.example.uifi95.redditclient.adapters.ArticleAdapter;
import com.example.uifi95.redditclient.controller.ArticleController;
import com.example.uifi95.redditclient.fragments.ArticleInputDialog;
import com.example.uifi95.redditclient.model.Article;
import com.example.uifi95.redditclient.repo.ArticleRepo;
import com.example.uifi95.redditclient.repo.IArticleRepo;
import com.example.uifi95.redditclient.repo.RealmArticleRepo;

import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;

/**
 * Created by uifi95 on 23.11.2016.
 */

public class ArticleListActivity extends AppCompatActivity {

    ListView articleList;
    ArrayAdapter adapter;
    IArticleRepo repo;
    ArticleController controller;

    public void updateArticle(int id, String title, String content, Date postDate){
        controller.updateArticle(id, title, content, postDate, 0);
        adapter.notifyDataSetChanged();
    }

    public void addArticle(String title, String content, Date postDate){
        System.out.println(controller.addArticle(title, content, postDate, 0).getTitle());
        adapter.notifyDataSetChanged();
    }

    public void removeArticle(int id){
        controller.removeArticle(id);
        adapter.notifyDataSetChanged();
    }

    public void setupAdapter(){
        Realm realm = Realm.getDefaultInstance();
        repo = new RealmArticleRepo(realm);
        controller =  new ArticleController(repo);

        setContentView(R.layout.activity_article_list);
        adapter = new ArticleAdapter(this, controller.getAllArticles());
    }

    public void addButton(){
        Button addButton = (Button) findViewById(R.id.addArticle);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle state = new Bundle();
                state.putBoolean("addState", true);
                ArticleInputDialog addDialog = new ArticleInputDialog();
                addDialog.setArguments(state);
                addDialog.show(getFragmentManager(), "addDialog");
            }
        });
    }

    public void addListView(){
        articleList = (ListView) findViewById(R.id.ArticleList);
        articleList.setAdapter(adapter);
        articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Article currentArticle = (Article) adapter.getItem(i);
                Bundle state = new Bundle();
                Calendar postDate = Calendar.getInstance();
                postDate.setTime(currentArticle.getPostDate());
                state.putBoolean("addState", false);
                state.putString("title", currentArticle.getTitle());
                state.putString("content", currentArticle.getContent());
                state.putInt("id", currentArticle.getId());
                state.putInt("day", postDate.get(Calendar.DAY_OF_MONTH));
                state.putInt("month", postDate.get(Calendar.MONTH));
                state.putInt("year", postDate.get(Calendar.YEAR));

                ArticleInputDialog editDialog = new ArticleInputDialog();
                editDialog.setArguments(state);
                editDialog.show(getFragmentManager(), "editDialog");
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(getApplicationContext());
        setupAdapter();
        addButton();
        addListView();
    }
}
