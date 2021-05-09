package com.heavycoders.myapplication.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heavycoders.myapplication.R;

public class InstagramFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    CardView gaurav, tanmay, pew, dude, strongestMan, barbel, larry, eddy, filter, tvf, yashraj, aib;

    public InstagramFragment() {

    }

    public static InstagramFragment newInstance(String param1, String param2) {
        InstagramFragment fragment = new InstagramFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_instagram, container, false);

        gaurav = root.findViewById(R.id.gaurav);
        tanmay = root.findViewById(R.id.tanmay);
        pew = root.findViewById(R.id.pew);
        dude = root.findViewById(R.id.dude);

        strongestMan = root.findViewById(R.id.strongestMan);
        barbel = root.findViewById(R.id.barbel);
        larry = root.findViewById(R.id.larry);
        eddy = root.findViewById(R.id.eddy);

        filter = root.findViewById(R.id.filter);
        tvf = root.findViewById(R.id.tvf);
        yashraj = root.findViewById(R.id.yashraj);
        aib = root.findViewById(R.id.aib);

        gaurav.setOnClickListener(v -> {
            String link = "https://www.instagram.com/taneja.gaurav/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        tanmay.setOnClickListener(v -> {
            String link = "https://www.instagram.com/tanmaybhat/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        pew.setOnClickListener(v -> {
            String link = "https://www.instagram.com/pewdiepie/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        dude.setOnClickListener(v -> {
            String link = "https://www.instagram.com/dudeperfect/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });



        strongestMan.setOnClickListener(v -> {
            String link = "https://www.instagram.com/theworldsstrongestman/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        barbel.setOnClickListener(v -> {
            String link = "https://www.instagram.com/barbellbrigade/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        larry.setOnClickListener(v -> {
            String link = "https://www.instagram.com/larry_gao/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        eddy.setOnClickListener(v -> {
            String link = "https://www.instagram.com/eddiehallwsm/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });



        filter.setOnClickListener(v -> {
            String link = "https://www.instagram.com/filtercopy/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        tvf.setOnClickListener(v -> {
            String link = "https://www.instagram.com/theviralfever/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        yashraj.setOnClickListener(v -> {
            String link = "https://www.instagram.com/yashrajmukhate/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });

        aib.setOnClickListener(v -> {
            String link = "https://www.instagram.com/allindiabakchod/";
            Intent instagram = new Intent(Intent.ACTION_VIEW);
            instagram.setData(Uri.parse(link));
            startActivity(instagram);
        });



        return root;
    }

}