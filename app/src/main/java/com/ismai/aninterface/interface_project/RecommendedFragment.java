package com.ismai.aninterface.interface_project;


import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
public class RecommendedFragment extends Fragment {


    public RecommendedFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    String[] rec_array_list;
    Location locatA;
    Double score;
    int sorting;
    int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recommended, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_recommended);
        ((NavDrawer) getActivity()).refreshOptionsMenu();
        ((NavDrawer) getActivity()).setFavInvisible();

        score = getArguments().getDouble("score");
        sorting = getArguments().getInt("sort");
        type = getArguments().getInt("type");

        locatA = new Location("A");
        locatA.setLatitude(getArguments().getDouble("lat"));
        locatA.setLongitude(getArguments().getDouble("lon"));


        final ArrayList<Place> places = getPlaces();

        Iterator<Place> placeIterator = places.iterator();

        while (placeIterator.hasNext()){
            Place place = placeIterator.next();
            if (place.getScore() < score ){
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

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Recommended");

        return view;
    }

    private ArrayList<Place> getPlaces(){
        rec_array_list = getResources().getStringArray(R.array.recommended_array);

        ArrayList<Place> places = new ArrayList<Place>();

        final ArrayList<String> arrayList = new ArrayList<String>();

        for (String s : rec_array_list) {
            String[] split = s.split(";");
            Location locatTemp = new Location("temp");
            locatTemp.setLatitude(Double.parseDouble(split[3]));
            locatTemp.setLongitude(Double.parseDouble(split[4]));
            double dist = locatA.distanceTo(locatTemp);
            Place place = new Place(split[0], split[1], Double.parseDouble(split[2]), Double.parseDouble(split[3]), Double.parseDouble(split[4]), dist, split[5], split[6], split[7], split[8], Double.parseDouble(split[9]));
            places.add(place);
        }
        return places;
    }



}
