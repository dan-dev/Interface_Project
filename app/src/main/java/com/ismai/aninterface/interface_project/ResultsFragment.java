package com.ismai.aninterface.interface_project;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


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
    int score;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_results, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_results);

        ((NavDrawer) getActivity()).refreshOptionsMenu();

        maxDist = Double.parseDouble(getArguments().getString("ratios"));
        score = Integer.parseInt(getArguments().getString("score"));

        locatA = new Location("A");
        locatA.setLatitude(41.267825);
        locatA.setLongitude(-8.617091);

        final ArrayList<Place> places = getPlaces();

        Collections.sort(places, new Comparator<Place>() {
            @Override
            public int compare(Place p1, Place p2) {
                if (p1.getDistance() > p2.getDistance())
                    return 1;
                if (p1.getDistance() < p2.getDistance())
                    return -1;
                return 0;
            }
        });

        Iterator<Place> placeIterator = places.iterator();

        while (placeIterator.hasNext()){
            Place place = placeIterator.next();
            if (place.getDistance() > maxDist || place.getScore() < score ){
                placeIterator.remove();
            }
        }

        ItemAdapter adapter = new ItemAdapter(view.getContext(), places);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Place place = places.get(i);
                Bundle bundle = new Bundle();
                bundle.putParcelable("place", place);
                PlaceDetailsFragment fragment = new PlaceDetailsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Results");

        return view;
    }

    private ArrayList<Place> getPlaces(){
        restaurant_array_list = getResources().getStringArray(R.array.restaurant_array);

        ArrayList<Place> places = new ArrayList<Place>();

        final ArrayList<String> arrayList = new ArrayList<String>();

        for (String s : restaurant_array_list) {
            String[] split = s.split(";");
            Location locatTemp = new Location("temp");
            locatTemp.setLatitude(Double.parseDouble(split[3]));
            locatTemp.setLongitude(Double.parseDouble(split[4]));
            double dist = locatA.distanceTo(locatTemp);
            Place place = new Place(split[0], split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), dist, split[5], split[6]);
            places.add(place);
        }
        return places;
    }
}