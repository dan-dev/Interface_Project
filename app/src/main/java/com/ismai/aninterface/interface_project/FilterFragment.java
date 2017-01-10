package com.ismai.aninterface.interface_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;

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

        String[] types = new String[4];
        String[] sorting = new String[5];

        types[0] = "All";
        types[1] = "Restaurants";
        types[2] = "Bars";
        types[3] = "Landmarks";

        sorting[0] = "None";
        sorting[1] = "Score";
        sorting[2] = "Distance";
        sorting[3] = "Favourites";
        sorting[4] = "Price";

        spn_sort.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, sorting));
        spn_type.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, types));

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ratios", range.getText().toString());
                bundle.putDouble("score", rating.getRating());
                bundle.putString("sort", spn_sort.getSelectedItem().toString());
                bundle.putString("type", spn_type.getSelectedItem().toString());

                if (frag.equalsIgnoreCase("ResultsFragment")){
                    ResultsFragment fragment = new ResultsFragment();
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragment.setArguments(bundle);
                    fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
                }
                else if (frag.equalsIgnoreCase("FavouritesFragment")){

                }
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle("Filter");

        return view;
    }
}