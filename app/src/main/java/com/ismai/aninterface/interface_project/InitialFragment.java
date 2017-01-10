package com.ismai.aninterface.interface_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InitialFragment extends Fragment {

    public InitialFragment() {
        // Required empty public constructor
    }

    double longitude = -8.617091;
    double latitude = 41.267825;

    LocationHandler locationHandler;
    View view;
    Button go_button;
    Button get_location;
    EditText searchContent;
    EditText ratiosContent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_initial, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();
        searchContent = (EditText) view.findViewById(R.id.position_box);
        ratiosContent = (EditText) view.findViewById(R.id.ratious_box);

        go_button = (Button) view.findViewById(R.id.go_button);
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ratios", ratiosContent.getText().toString());
                bundle.putDouble("score", 0);
                bundle.putString("sort", "None");
                bundle.putDouble("lat", latitude);
                bundle.putDouble("lon", longitude);
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
                locationHandler = new LocationHandler(getContext());
                if(locationHandler.canGetLocation()){
                    latitude = locationHandler.getLatitude();
                    longitude = locationHandler.getLongitude();

                    Toast.makeText(getContext(), "Your Location is - \nLat: "
                            + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();

                    //locationHandler.stopUsingGPS();
                }else{
                    locationHandler.showSettingsAlert();
                }
            }
        });

        return view;
    }
}