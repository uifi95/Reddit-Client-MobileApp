package com.example.uifi95.redditclient.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.uifi95.redditclient.R;
import com.example.uifi95.redditclient.fragments.ChartFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button articleRepoButton = (Button) findViewById(R.id.ArticleRepo);
        articleRepoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ArticleListActivity.class));
            }
        });

        Button emailSendButton = (Button) findViewById(R.id.EmailSend);
        emailSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] TO = {"someone@gmail.com"};
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("mailto:"));
                emailIntent.setType("text/plain");
                TextView emailBody = (TextView) findViewById(R.id.EmailBody);

                emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, emailBody.getText());
                startActivity(emailIntent);
            }
        });

        Button chartButton = (Button) findViewById(R.id.ChartButton);
        chartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ChartFragment().show(getFragmentManager(), "chart");
            }
        });

    }
}
