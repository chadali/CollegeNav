package com.vappna.collegenav;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnair on 9/26/2015.
 */
public class ListAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final List<String> collegeTitles;

    public ListAdapter(Activity context, List<String> collegeTitles) {
        super(context, R.layout.list_layout, collegeTitles);
        this.context = context;
        this.collegeTitles = collegeTitles;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_layout, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(collegeTitles.get(position));

        imageView.setImageResource(R.drawable.academic_icon);
        return rowView;
    }

}
