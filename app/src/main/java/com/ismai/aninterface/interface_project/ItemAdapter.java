package com.ismai.aninterface.interface_project;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.support.v7.widget.AppCompatRadioButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class ItemAdapter extends ArrayAdapter<Place>{

    Context context;
    String[] imageStrings;
    String[] titleStrings;
    String[] scoreStrings;
    String[] distanceStrings;
    String[] typeStrings;
    String[] timeStrings;
    String[] priceStrings;

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
        ArrayList<String> typeStrings = new ArrayList<String>();
        ArrayList<String> timeStrings = new ArrayList<String>();
        ArrayList<String> priceStrings = new ArrayList<String>();

        for (Place place : items){
            titleStrings.add(place.getName());
            String distance = "";
            if (place.getDistance() > 1000){ distance = String.format("%.2f km", place.getDistance()/1000); }
            else { distance = String.format("%.0f m", place.getDistance()); }
            distanceStrings.add(distance);
            scoreStrings.add(Double.toString(place.getScore()));
            imageStrings.add(place.getImageLink());
            typeStrings.add(place.getType());
            timeStrings.add(place.getTime());
            priceStrings.add(Double.toString(place.getPrice()));
        }
        this.context = context;
        this.imageStrings = imageStrings.toArray(new String[imageStrings.size()]);
        this.titleStrings = titleStrings.toArray(new String[titleStrings.size()]);
        this.scoreStrings = scoreStrings.toArray(new String[scoreStrings.size()]);
        this.distanceStrings = distanceStrings.toArray(new String[distanceStrings.size()]);
        this.typeStrings = typeStrings.toArray(new String[typeStrings.size()]);
        this.timeStrings = timeStrings.toArray(new String[timeStrings.size()]);
        this.priceStrings = priceStrings.toArray(new String[priceStrings.size()]);
    }

    static class ViewHolder{
        TextView title;
        TextView score;
        TextView distance;
        ImageView image;
        RelativeLayout relativeLayout;
        TextView time;
        TextView price;
        ImageView open_closed;
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
            holder.relativeLayout = (RelativeLayout) convertView.findViewById(R.id.itemlayout_relative);
            holder.time = (TextView) convertView.findViewById(R.id.time_text_item);
            holder.price = (TextView) convertView.findViewById(R.id.price_text_item);
            holder.open_closed = (ImageView) convertView.findViewById(R.id.image_open_closed);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        String img = imageStrings[position];
        holder.title.setText(titleStrings[position]);
        holder.distance.setText(getContext().getResources().getString(R.string.item_distance) + distanceStrings[position]);
        holder.score.setText(getContext().getResources().getString(R.string.item_score) + scoreStrings[position]);
        holder.time.setText(getContext().getResources().getString(R.string.item_time) + timeStrings[position]);
        holder.price.setText(getContext().getResources().getString(R.string.item_price) + priceStrings[position]);
        String[] tmpSlt = timeStrings[position].split("-");
        int time = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        if (time < 5) { time += 24; }
        if ( Integer.parseInt(tmpSlt[1]) < 5)
        {
            int tmp = Integer.parseInt(tmpSlt[1]) + 24;
            tmpSlt[1] = Integer.toString(tmp);
        }
        if (time > Integer.parseInt(tmpSlt[0]) && time < Integer.parseInt(tmpSlt[1])){
            holder.open_closed.setBackgroundResource(R.drawable.btn_open);
        } else {
            holder.open_closed.setBackgroundResource(R.drawable.btn_close);
        }
        switch (typeStrings[position]){
            case "r":
                holder.relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorRestaurant));
                break;
            case "b":
                holder.relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorBar));
                break;
            case "l":
                holder.relativeLayout.setBackgroundColor(getContext().getResources().getColor(R.color.colorLandmark));
                break;
            default:
                break;
        }
        Picasso.with(context).load(img).fit().placeholder(R.drawable.thumbnail).into(holder.image);
        return convertView;
    }
}