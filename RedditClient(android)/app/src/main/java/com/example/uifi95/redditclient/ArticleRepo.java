package com.example.uifi95.redditclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by uifi95 on 23.11.2016.
 */

public class ArticleRepo extends AppCompatActivity {

    ListView articleList;
    SimpleAdapter adapter;

    public void updateDataSource(int index, String title, String content){
        Map<String, String> item = (Map<String, String>) articleList.getItemAtPosition(index);
        item.put("title", title);
        item.put("content", content);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list);

        List<Map<String, String>> data = new ArrayList<>();
        for(int i=0; i<4; i++) {
            Map<String, String> datam = new HashMap<>();
            datam.put("title", "article"+ Integer.toString(i));
            datam.put("content", "content");
            data.add(datam);
        }

        adapter = new SimpleAdapter(this, data,
                android.R.layout.simple_list_item_2,
                new String[] {"title", "content"},
                new int[] {android.R.id.text1,
                        android.R.id.text2});

        articleList = (ListView) findViewById(R.id.ArticleList);
        articleList.setAdapter(adapter);

        articleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent editItem = new Intent(ArticleRepo.this, ArticleListEdit.class);
                Map<String, String> selectedFromList = (Map<String, String>) articleList.getItemAtPosition(i);
                editItem.putExtra("index", i);
                editItem.putExtra("title", selectedFromList.get("title"));
                editItem.putExtra("content", selectedFromList.get("content"));

                startActivityForResult(editItem, 1);
            }
        });
    }

    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        // Collect data from the intent and use it
        if (requestCode == 1 && data != null) {
            Bundle extras = data.getExtras();
            updateDataSource(extras.getInt("index"), extras.getString("title"), extras.getString("content"));
        }
    }
}
