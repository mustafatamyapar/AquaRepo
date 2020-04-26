package com.example.aquaassistant.zulal;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.aquaassistant.R;
import com.example.aquaassistant.zulal.Faq;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter {
private ArrayList<Faq> listFaq;
private Activity activity;
public MyAdapter(ArrayList<Faq> listfaq, Activity activity)
{
    super(activity, R.layout.activity_faq,listfaq)
    this.listFaq= listfaq;
    this.activity = activity;
}

@NonNull
@Override
public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    LayoutInflater layoutInflater = activity.getLayoutInflater();
    View customView =layoutInflater.inflate(R.layout.activity_faq,null,true);
    TextView question = customView.findViewById(R.id.listView);
    question.setText(listFaq.get(position).getQuestion());
    return customView;
}
}
