package com.ismai.aninterface.interface_project;


import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultsFragment extends Fragment {


    public ResultsFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    String[] restaurant_array_list;
    Location locatA;
    Double maxDist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Results");

        view = inflater.inflate(R.layout.fragment_results, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_results);

        maxDist = Double.parseDouble(getArguments().getString("ratios"));

        locatA = new Location("A");
        locatA.setLatitude(41.267825);
        locatA.setLongitude(-8.617091);

        restaurant_array_list = getResources().getStringArray(R.array.restaurant_array);

        final ArrayList<String> arrayList = new ArrayList<String>();

        for (String s : restaurant_array_list) {
            String[] split = s.split(";");

            Location locatTemp = new Location("temp");
            locatTemp.setLatitude(Double.parseDouble(split[3]));
            locatTemp.setLongitude(Double.parseDouble(split[4]));
            double dist = locatA.distanceTo(locatTemp);
            if(dist <= maxDist){
                if (dist > 1000){
                    dist = dist / 1000;
                    arrayList.add(String.format(split[0] + " | Distance: %.2f km", dist));
                }
                else {
                    arrayList.add(String.format(split[0] + " | Distance: %.2f m", dist));
                }

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            String itm = arrayList.get(i);
            String[] splt = itm.split(" \\| ");
            for (String s : restaurant_array_list) {
                if(s.startsWith(splt[0])){
                    String[] split = s.split(";");
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pt/maps/@"+ split[3] +","+ split[4] +",17z"));
                    startActivity(browserIntent);
                }
            }
            }
        });

        return view;
    }

}
