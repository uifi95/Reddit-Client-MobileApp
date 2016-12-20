package com.example.uifi95.redditclient.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.uifi95.redditclient.R;
import com.example.uifi95.redditclient.activities.ArticleListActivity;
import com.example.uifi95.redditclient.model.Article;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by uifi95 on 11.12.2016.
 */

public class ArticleInputDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Bundle args = getArguments();
        final boolean addState = args.getBoolean("addState");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View articleInputView = inflater.inflate(R.layout.article_input, null);

        final EditText title = (EditText) articleInputView.findViewById(R.id.title);
        final EditText content = (EditText) articleInputView.findViewById(R.id.content);
        final DatePicker postDate = (DatePicker) articleInputView.findViewById(R.id.postDate);


        if (!addState) {
            title.setText(args.getString("title"));
            content.setText(args.getString("content"));
            postDate.updateDate(args.getInt("year"), args.getInt("month"), args.getInt("day"));
        }
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(articleInputView)
                // Add action buttons
                .setPositiveButton(addState ? "ADD" : "EDIT", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ArticleListActivity list = (ArticleListActivity) getActivity();
                        Calendar calendar = Calendar.getInstance();
                        calendar.set(postDate.getYear(), postDate.getMonth(), postDate.getDayOfMonth());
                        if (!addState){
                            list.updateArticle(args.getInt("id"),
                                    title.getText().toString(),
                                    content.getText().toString(),
                                    calendar.getTime());
                        } else {
                            list.addArticle(title.getText().toString(),
                                    content.getText().toString(),
                                    calendar.getTime());
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ArticleInputDialog.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
