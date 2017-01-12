package com.ismai.aninterface.interface_project;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ismai.aninterface.interface_project.R;

public class ReviewFragment extends Fragment {


    public ReviewFragment() {
        // Required empty public constructor
    }

    View view;
    Button submit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_review, container, false);
        ((NavDrawer) getActivity()).setFavInvisible();
        submit = (Button) view.findViewById(R.id.submit_review);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), getResources().getString(R.string.review_submited), Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}