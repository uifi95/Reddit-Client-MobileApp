package com.example.uifi95.redditclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by uifi95 on 23.11.2016.
 */

public class ArticleListEdit extends AppCompatActivity {
    Bundle extras;
    TextView editTitle;
    TextView editContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_list_edit);

        extras = getIntent().getExtras();
        editTitle = (TextView) findViewById(R.id.EditArticleTitle);
        editContent = (TextView) findViewById(R.id.EditArticleContent);
        editTitle.setText(extras.getString("title"));
        editContent.setText(extras.getString("content"));

    }

    @Override
    public void onBackPressed() {
        final int index = extras.getInt("index");
        Intent intent = new Intent();
        intent.putExtra("index", index);
        intent.putExtra("title", editTitle.getText().toString());
        intent.putExtra("content", editContent.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
        super.onBackPressed();
    }
}
