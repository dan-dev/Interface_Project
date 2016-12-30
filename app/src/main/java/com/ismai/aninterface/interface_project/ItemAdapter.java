package com.ismai.aninterface.interface_project;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemAdapter extends ArrayAdapter<Place>{

    Context context;
    String[] imageStrings;
    String[] titleStrings;
    String[] scoreStrings;
    String[] distanceStrings;

    LayoutInflater inflater;

    public ItemAdapter(Context context, ArrayList<Place> items /*String[] imageStrings, String[] titleStrings,
                       String[] scoreStrings, String[] distanceStrings */)
    {
        //super(context, R.layout.listview_item_layout, titleStrings);
        super(context, R.layout.listview_item_layout, items);

        ArrayList<String> imageStrings = new ArrayList<String>();
        ArrayList<String> titleStrings = new ArrayList<String>();
        ArrayList<String> scoreStrings = new ArrayList<String>();
        ArrayList<String> distanceStrings = new ArrayList<String>();

        for (Place place : items){
            titleStrings.add(place.getName());
            String distance = "";
            if (place.getDistance() > 1000){ distance = String.format("%.2f km", place.getDistance()/1000); }
            else { distance = String.format("%.0f m", place.getDistance()); }
            distanceStrings.add(distance);
            scoreStrings.add(Double.toString(place.getScore()));
            imageStrings.add(place.getImageLink());
        }
        this.context = context;
        this.imageStrings = imageStrings.toArray(new String[imageStrings.size()]);
        this.titleStrings = titleStrings.toArray(new String[titleStrings.size()]);
        this.scoreStrings = scoreStrings.toArray(new String[scoreStrings.size()]);
        this.distanceStrings = distanceStrings.toArray(new String[distanceStrings.size()]);
    }

    static class ViewHolder{
        TextView title;
        TextView score;
        TextView distance;
        ImageView image;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_layout, null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.title_text_item);
            holder.score = (TextView) convertView.findViewById(R.id.score_text_item);
            holder.distance = (TextView) convertView.findViewById(R.id.distance_text_item);
            holder.image = (ImageView) convertView.findViewById(R.id.image_item);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        String img = imageStrings[position];
        holder.title.setText("Name: " + titleStrings[position]);
        holder.distance.setText("Distance: " + distanceStrings[position]);
        holder.score.setText("Score: " + scoreStrings[position]);
        Picasso.with(context).load(img).fit().placeholder(R.drawable.thumbnail).into(holder.image);
        return convertView;
    }
}