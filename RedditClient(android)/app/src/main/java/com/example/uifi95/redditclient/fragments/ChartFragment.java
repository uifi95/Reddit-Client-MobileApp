package com.example.uifi95.redditclient.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.uifi95.redditclient.R;
import com.example.uifi95.redditclient.activities.ArticleListActivity;
import com.example.uifi95.redditclient.controller.ArticleController;
import com.example.uifi95.redditclient.model.Article;
import com.example.uifi95.redditclient.repo.ArticleRepo;
import com.example.uifi95.redditclient.repo.RealmArticleRepo;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.realm.Realm;

/**
 * Created by uifi95 on 19.12.2016.
 */

public class ChartFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View chartView = inflater.inflate(R.layout.chart, null);
        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        Realm.init(getContext());
        ArticleController dummyController = new ArticleController(new RealmArticleRepo(Realm.getDefaultInstance()));
        List<PieEntry> entries = new ArrayList<>();
        List<LegendEntry> labels = new ArrayList<>();
        Map<String, Integer> dates = new HashMap<>();
        for (Article article : dummyController.getAllArticles()){
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String key = df.format(article.getPostDate());
            if (dates.containsKey(key)){
                dates.put(key, dates.get(key) + 1);
            } else {
                dates.put(key, 1);
            }
        }
        int i = 0;
        for (String key : dates.keySet()){
            LegendEntry entry = new LegendEntry();
            entry.label = key;
            entry.formColor = ColorTemplate.VORDIPLOM_COLORS[i];
            labels.add(entry);
            entries.add(new PieEntry(dates.get(key)));
            i++;
        }
        PieChart chart = (PieChart) chartView.findViewById(R.id.chart);
        Legend legend =chart.getLegend();
        legend.setEnabled(true);
        legend.setCustom(labels);
        PieDataSet dataset = new PieDataSet(entries, "");
        dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
        PieData data = new PieData(dataset);
        chart.setData(data);
        Description desc = new Description();
        desc.setEnabled(true);
        desc.setText("Number of articles per date");
        chart.setDescription(desc);
        builder.setView(chartView);
        return builder.create();
    }
}
