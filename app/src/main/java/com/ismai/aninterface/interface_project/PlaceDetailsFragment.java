package com.ismai.aninterface.interface_project;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;


/**
 * A simple {@link Fragment} subclass.
 */
public class PlaceDetailsFragment extends Fragment {


    public PlaceDetailsFragment() {
        // Required empty public constructor
    }

    View view;
    TextView description;
    TextView contacts;
    TextView score;
    ImageView image;

    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place_details, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();

        image = (ImageView) view.findViewById(R.id.image_place);

        score = (TextView) view.findViewById(R.id.score_place);
        contacts = (TextView) view.findViewById(R.id.contact_place);
        description = (TextView) view.findViewById(R.id.details_place);
        button = (Button) view.findViewById(R.id.button_go_place);

        //String data = getArguments().getString("data");

        final Place place = getArguments().getParcelable("place");

        Picasso.with(getContext()).load(place.getImageLink()).placeholder(R.drawable.thumbnail).into(image);
        description.setText(place.getDescription());
        score.setText("Score: " + place.getScore());
        contacts.setText("Contact: " + place.getContact());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pt/maps/@"+ place.getLatitude() +","+ place.getLongitude() +",17z"));
                startActivity(browserIntent);
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle(place.getName());

        /*final String[] split = data.split(";");

        Picasso.with(getContext()).load(split[6]).placeholder(R.drawable.thumbnail).into(image);

        try {
            Bitmap bitmap = (new WebDataHandler(image).execute(split[6]).get());
            image.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        description.setText(split[1]);
        score.setText("Score: " + split[2]);
        contacts.setText("Contacts: " + split[5]);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pt/maps/@"+ split[3] +","+ split[4] +",17z"));
                startActivity(browserIntent);
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle(split[0]);*/

        return view;
    }

}
