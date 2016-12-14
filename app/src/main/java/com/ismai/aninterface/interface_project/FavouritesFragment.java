package com.ismai.aninterface.interface_project;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment {


    public FavouritesFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;
    String[] favArray;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_favourites, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_favourite);

        /*ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("3");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("6");
        arrayList.add("7");
        arrayList.add("8");
        arrayList.add("9");
        arrayList.add("10");
        arrayList.add("11");
        arrayList.add("12");
        arrayList.add("13");
        arrayList.add("14");
        arrayList.add("15");

        String[] ar = new String[arrayList.size()];
        ar = arrayList.toArray(ar);*/

        favArray = getResources().getStringArray(R.array.favourites_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, favArray);

        listView.setAdapter(adapter);

        return view;
    }

}
