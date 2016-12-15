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
public class RecommendedFragment extends Fragment {


    public RecommendedFragment() {
        // Required empty public constructor
    }

    View view;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_recommended, container, false);
        listView = (ListView) view.findViewById(R.id.list_view_recommended);

        ArrayList<String> arrayList = new ArrayList<String>();

        arrayList.add("1 - Because you liked - 20");
        arrayList.add("2 - Because you liked - 21");
        arrayList.add("3 - Because you liked - 22");
        arrayList.add("4 - Because you liked - 23");
        arrayList.add("5 - Because you liked - 24");
        arrayList.add("6 - Because you liked - 25");
        arrayList.add("7 - Because you liked - 26");
        arrayList.add("8 - Because you liked - 27");
        arrayList.add("9 - Because you liked - 29");
        arrayList.add("10 - Because you liked - 30");
        arrayList.add("11 - Because you liked - 31");
        arrayList.add("12 - Because you liked - 32");
        arrayList.add("13 - Because you liked - 33");
        arrayList.add("14 - Because you liked - 34");
        arrayList.add("15 - Because you liked - 35");

        String[] ar = new String[arrayList.size()];
        ar = arrayList.toArray(ar);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, ar);

        listView.setAdapter(adapter);

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Recommended");

        return view;
    }





}
