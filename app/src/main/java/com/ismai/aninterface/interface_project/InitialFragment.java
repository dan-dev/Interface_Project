package com.ismai.aninterface.interface_project;


import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InitialFragment extends Fragment {


    public InitialFragment() {
        // Required empty public constructor
    }

    View view;
    Button go_button;
    Button get_location;
    EditText searchContent;
    TextView distancetext;
    EditText ratiosContent;
    float dist;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_initial, container, false);
        searchContent = (EditText) view.findViewById(R.id.position_box);
        ratiosContent = (EditText) view.findViewById(R.id.ratious_box);


        go_button = (Button) view.findViewById(R.id.go_button);
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ratios", ratiosContent.getText().toString());
                ResultsFragment fragment = new ResultsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            }
        });

        get_location = (Button) view.findViewById(R.id.get_position_button);
        get_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pt/maps/place/Restaurante+Madureira's/@41.207084,-8.5668552,20.08z/data=!4m5!3m4!1s0x0:0xe168a8b4893d40b7!8m2!3d41.2070522!4d-8.5670024?hl=en"));
                startActivity(browserIntent);
                //LocationManager lm = (LocationManager)getSystemService()

            }
        });

        Location locatA = new Location("A");
        locatA.setLatitude(41.207014);
        locatA.setLongitude(-8.567125);

        Location locatB = new Location("B");
        locatB.setLatitude(41.204615);
        locatB.setLongitude(-8.555773);

        distancetext = (TextView) view.findViewById(R.id.text_dist);
        dist = locatA.distanceTo(locatB);

        distancetext.setText(Float.toString(dist)+"km");

        return view;
    }
}
