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


public class ResultsFragment extends Fragment {

    public ResultsFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    String[] restaurant_array_list;
    Location locatA;
    Double maxDist;
    Double score;
    int sorting;
    int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_results, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_results);

        ((NavDrawer) getActivity()).refreshOptionsMenu();
        ((NavDrawer) getActivity()).setFavInvisible();

        maxDist = Double.parseDouble(getArguments().getString("ratios"));
        score = getArguments().getDouble("score");
        sorting = getArguments().getInt("sort");
        type = getArguments().getInt("type");

        locatA = new Location("A");
        locatA.setLatitude(getArguments().getDouble("lat"));
        locatA.setLongitude(getArguments().getDouble("lon"));

        ArrayList<Place> tempPlaces = getPlaces();
        ArrayList<Place> filterPlaces = new ArrayList<>();

        if (type == 0){
            filterPlaces = tempPlaces;
        } else if (type == 1){
            for(Place p : tempPlaces){
                if (p.getType().equals("r") ){
                    filterPlaces.add(p);
                }
            }
        } else if (type == 2){
            for(Place p : tempPlaces){
                if (p.getType().equals("b") ){
                    filterPlaces.add(p);
                }
            }
        } else if (type == 3){
            for(Place p : tempPlaces){
                if (p.getType().equals("l") ){
                    filterPlaces.add(p);
                }
            }
        }

        final ArrayList<Place> places = filterPlaces;

        switch (sorting){
            case 0:
                break;
            case 1:
                Collections.sort(places, new Comparator<Place>() {
                    @Override
                    public int compare(Place p1, Place p2) {
                        if (p1.getScore() < p2.getScore())
                            return 1;
                        if (p1.getScore() > p2.getScore())
                            return -1;
                        return 0;
                    }
                });
                break;
            case 2:
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
                break;
            case 3:
                Collections.sort(places, new Comparator<Place>() {
                    @Override
                    public int compare(Place p1, Place p2) {
                        if (p1.getPrice() > p2.getPrice())
                            return 1;
                        if (p1.getPrice() < p2.getPrice())
                            return -1;
                        return 0;
                    }
                });
                break;
            default:

                break;
        }

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
        restaurant_array_list = getResources().getStringArray(R.array.places_array);

        ArrayList<Place> places = new ArrayList<Place>();

        final ArrayList<String> arrayList = new ArrayList<String>();

        for (String s : restaurant_array_list) {
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