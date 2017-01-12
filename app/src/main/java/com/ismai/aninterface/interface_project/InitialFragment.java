package com.ismai.aninterface.interface_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Geocoder;
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
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InitialFragment extends Fragment {

    public InitialFragment() {
        // Required empty public constructor
    }

    double latitude = 41.267825;
    double longitude = -8.617091;

    LocationHandler locationHandler;
    View view;
    Button go_button;
    Button get_location;
    EditText ratiosContent;
    AutoCompleteTextView autoCompleteTextView;

    String[] locationList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_initial, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();
        ((NavDrawer) getActivity()).setFavInvisible();
        ratiosContent = (EditText) view.findViewById(R.id.ratious_box);
        autoCompleteTextView = (AutoCompleteTextView) view.findViewById(R.id.autocomplete_text);

        locationList = getResources().getStringArray(R.array.locations);
        final List<Location> locatList = new ArrayList<>();
        final List<String> locationAutoComplete = new ArrayList<>();

        for (String s : locationList){
            String[] split = s.split(";");
            locationAutoComplete.add(split[0]);
            Location l = new Location(split[0]);
            l.setLatitude(Double.parseDouble(split[1]));
            l.setLongitude(Double.parseDouble(split[2]));
            locatList.add(l);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, locationAutoComplete);

        autoCompleteTextView.setAdapter(adapter);

        go_button = (Button) view.findViewById(R.id.go_button);
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String distance = "1000";
                if(!ratiosContent.getText().toString().equals("")){ distance = ratiosContent.getText().toString(); }
                bundle.putString("ratios", distance);
                bundle.putDouble("score", 0);
                bundle.putInt("sort", 0);
                bundle.putDouble("lat", latitude);
                bundle.putDouble("lon", longitude);
                ResultsFragment fragment = new ResultsFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();

                SharedPreferences preferences;
                SharedPreferences.Editor editor;
                preferences = getActivity().getPreferences(Context.MODE_WORLD_WRITEABLE);
                editor = preferences.edit();
                editor.putString("lat", ""+latitude);
                editor.putString("lon", ""+longitude);
                editor.commit();
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

                    Toast.makeText(getContext(), getResources().getString(R.string.location_set_gps), Toast.LENGTH_LONG).show();
                }else{
                    locationHandler.showSettingsAlert();
                }
            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                latitude = locatList.get(i).getLatitude();
                longitude = locatList.get(i).getLongitude();
                Toast.makeText(getContext(), getResources().getString(R.string.location_set_search) + locationAutoComplete.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}