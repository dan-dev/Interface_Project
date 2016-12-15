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

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ItemAdapter extends ArrayAdapter<String>{

    Context context;
    String[] imageStrings;
    String[] titleStrings;
    String[] scoreStrings;
    String[] distanceStrings;

    LayoutInflater inflater;


    public ItemAdapter(Context context, ArrayList<String> items /*String[] imageStrings, String[] titleStrings,
                       String[] scoreStrings, String[] distanceStrings */)
    {
        //super(context, R.layout.listview_item_layout, titleStrings);
        super(context, R.layout.listview_item_layout, items);

        ArrayList<String> imageStrings = new ArrayList<String>();
        ArrayList<String> titleStrings = new ArrayList<String>();
        ArrayList<String> scoreStrings = new ArrayList<String>();
        ArrayList<String> distanceStrings = new ArrayList<String>();

        for (String s : items){
            String[] splt = s.split(";");
            titleStrings.add(splt[0]);
            distanceStrings.add(splt[1]);
            scoreStrings.add(splt[2]);
            imageStrings.add(splt[3]);
            //scoreStrings.add("score");
            /*imageStrings.add("http://www.chiswickish.co.uk/blog/wp-content/uploads/2011/10/mat" +
                    "-does-chiswick-mat-smith-photography-blog-the-cabin-restaurant-outside-dining.jpg");*/
        }
        this.context = context;
        this.imageStrings = imageStrings.toArray(new String[imageStrings.size()]);
        this.titleStrings = titleStrings.toArray(new String[titleStrings.size()]);
        this.scoreStrings = scoreStrings.toArray(new String[scoreStrings.size()]);
        this.distanceStrings = distanceStrings.toArray(new String[distanceStrings.size()]);
    }

    public class ViewHolder{
        TextView title;
        TextView score;
        TextView distance;
        ImageView image;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View rowView = inflater.inflate(R.layout.listview_item_layout);

        if(convertView == null){
          inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_layout, null);
        }

        ViewHolder holder = new ViewHolder();

        holder.title = (TextView) convertView.findViewById(R.id.title_text_item);
        holder.score = (TextView) convertView.findViewById(R.id.score_text_item);
        holder.distance = (TextView) convertView.findViewById(R.id.distance_text_item);
        holder.image = (ImageView) convertView.findViewById(R.id.image_item);

        holder.title.setText("Name: " + titleStrings[position]);
        holder.distance.setText("Distance: " + distanceStrings[position]);
        holder.score.setText("Score: " + scoreStrings[position]);
        try {
            //Bitmap bitmap = ( new WebDataHandler().execute(imageStrings[position]).get());

            WebDataHandler web = new WebDataHandler();
            //web.setContext(getContext());
            Bitmap bitmap = ( web.execute(imageStrings[position]).get());
            holder.image.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        /*
        try {
            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(imageStrings[position]).getContent());
            holder.image.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        //holder.image.setImageResource();


        return convertView;
    }



}
