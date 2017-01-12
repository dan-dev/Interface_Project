package com.ismai.aninterface.interface_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterFragment extends Fragment {

    public FilterFragment() {
        // Required empty public constructor
    }

    EditText range;

    View view;
    Spinner spn_type;
    Button apply;
    Spinner spn_sort;
    RatingBar rating;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();

        final String frag = getArguments().getString("fragment");
        range = (EditText) view.findViewById(R.id.ratious_box);
        rating = (RatingBar) view.findViewById(R.id.rating_stars);

        spn_sort = (Spinner) view.findViewById(R.id.spin_sorting);
        spn_type = (Spinner) view.findViewById(R.id.spin_type);
        apply = (Button) view.findViewById(R.id.button_submit);

        String[] types;
        String[] sorting;

        types = getResources().getStringArray(R.array.filter_type_list);

        sorting = getResources().getStringArray(R.array.filter_sorting_list);

        spn_sort.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, sorting));
        spn_type.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, types));

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences;
                preferences = getActivity().getPreferences(Context.MODE_WORLD_READABLE);
                ((NavDrawer) getActivity()).setFavInvisible();

                Bundle bundle = new Bundle();
                bundle.putDouble("score", rating.getRating());
                bundle.putDouble("lat", Double.parseDouble(preferences.getString("lat", "0")));
                bundle.putDouble("lon", Double.parseDouble(preferences.getString("lon", "0")));

                bundle.putInt("sort", spn_sort.getSelectedItemPosition());
                bundle.putInt("type", spn_type.getSelectedItemPosition());

                if (range.getText().toString() == ""){
                    bundle.putString("ratios", "1000");
                } else {
                    bundle.putString("ratios", range.getText().toString());
                }

                if (frag.equalsIgnoreCase("ResultsFragment")){
                    ResultsFragment fragment = new ResultsFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                }
                else if (frag.equalsIgnoreCase("FavouritesFragment")){
                    FavouritesFragment fragment = new FavouritesFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                }
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Filter");

        return view;
    }
}