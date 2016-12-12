package com.example.uifi95.redditclient.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.uifi95.redditclient.R;
import com.example.uifi95.redditclient.activities.ArticleListActivity;
import com.example.uifi95.redditclient.model.Article;

import java.util.List;

/**
 * Created by uifi95 on 11.12.2016.
 */

public class ArticleAdapter extends ArrayAdapter<Article> {
    private List<Article> articles;
    private LayoutInflater  mInflater;

    public ArticleAdapter(Context context, List<Article> articles) {
        super(context, 0, articles);
        this.articles = articles;
        mInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.article,parent , false);
            holder = new ViewHolder();
            holder.title =(TextView) convertView.findViewById(R.id.title);
            holder.content =(TextView) convertView.findViewById(R.id.content);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }

        Button deleteButton = (Button) convertView.findViewById(R.id.deleteArticle);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArticleListActivity list = (ArticleListActivity) getContext();
                list.removeArticle(articles.get(position).getId());
            }
        });

        Article article = articles.get(position);
        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());
        holder.deleteArticle = deleteButton;

        return convertView;
    }

    private static class ViewHolder{
        TextView title;
        TextView content;
        Button deleteArticle;
    }

}
