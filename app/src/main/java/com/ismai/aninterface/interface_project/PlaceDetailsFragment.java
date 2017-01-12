package com.ismai.aninterface.interface_project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class PlaceDetailsFragment extends Fragment {


    public PlaceDetailsFragment() {
        // Required empty public constructor
    }

    View view;
    TextView description;
    TextView contacts;
    TextView score;
    ImageView image;
    TextView time;
    TextView price;

    Button review;
    Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_place_details, container, false);
        ((NavDrawer) getActivity()).refreshOptionsMenu();
        ((NavDrawer) getActivity()).setFavVisible();

        image = (ImageView) view.findViewById(R.id.image_place);

        score = (TextView) view.findViewById(R.id.score_place);
        contacts = (TextView) view.findViewById(R.id.contact_place);
        description = (TextView) view.findViewById(R.id.details_place);
        button = (Button) view.findViewById(R.id.button_go_place);
        review = (Button) view.findViewById(R.id.button_review);
        time = (TextView) view.findViewById(R.id.time_place);
        price = (TextView) view.findViewById(R.id.price_place);

        final Place place = getArguments().getParcelable("place");

        Picasso.with(getContext()).load(place.getImageLink()).placeholder(R.drawable.thumbnail).into(image);
        description.setText(place.getDescription());
        score.setText(getResources().getString(R.string.place_score) + place.getScore());
        contacts.setText(getResources().getString(R.string.place_contact) + place.getContact());
        price.setText(getResources().getString(R.string.place_price) + place.getPrice() + "€");
        time.setText(getResources().getString(R.string.place_schedule) + place.getTime());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.pt/maps/@"+ place.getLatitude() +","+ place.getLongitude() +",17z"));
                startActivity(browserIntent);
            }
        });

        ((NavDrawer)getActivity()).getSupportActionBar().setTitle(place.getName());

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReviewFragment fragment = new ReviewFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_layout, fragment).addToBackStack(null).commit();
            }
        });

        return view;
    }
}
