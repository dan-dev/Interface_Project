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
import android.widget.Spinner;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FilterFragment extends Fragment {


    public FilterFragment() {
        // Required empty public constructor
    }

    EditText range;

    View view;
    Spinner spn_score;
    Spinner spn_type;
    Button apply;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_filter, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();

        //String distance = getArguments().getString("distance");
        final String frag = getArguments().getString("fragment");
        range = (EditText) view.findViewById(R.id.ratious_box);
        //range.setText(distance);

        spn_type = (Spinner) view.findViewById(R.id.spin_type);
        spn_score = (Spinner) view.findViewById(R.id.spin_score);
        apply = (Button) view.findViewById(R.id.button_submit);

        String[] types = new String[4];
        final String[] scores = new String[5];

        types[0] = "All";
        types[1] = "Restaurants";
        types[2] = "Bars";
        types[3] = "Landmarks";

        scores[0] = "All";
        scores[1] = "Higher than 1 star";
        scores[2] = "Higher than 2 stars";
        scores[3] = "Higher than 3 stars";
        scores[4] = "Higher than 4 stars";

        spn_type.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, types));
        spn_score.setAdapter(new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, scores));

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ratios", range.getText().toString());
                /*if (spn_score.getSelectedItem().toString().equalsIgnoreCase(scores[0])){
                    bundle.putString("score", "0");
                }
                else {*/
                    bundle.putString("score", ""+spn_score.getSelectedItemPosition());
                //}
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