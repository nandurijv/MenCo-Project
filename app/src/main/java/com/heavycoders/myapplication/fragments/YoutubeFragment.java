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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link YoutubeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class YoutubeFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    CardView yLarry, yBarbell, yNoel, yTone, yDude, yThat, yPew, yScoop, yFlying, yNikhil, yGaurav, yTanee;


    public YoutubeFragment() {
    }
    public static YoutubeFragment newInstance(String param1, String param2) {
        YoutubeFragment fragment = new YoutubeFragment();
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
        View root = inflater.inflate(R.layout.fragment_youtube, container, false);

        yLarry = root.findViewById(R.id.yLarry);
        yBarbell = root.findViewById(R.id.yBarbell);
        yNoel = root.findViewById(R.id.yNoel);
        yTone = root.findViewById(R.id.yTone);

        yDude = root.findViewById(R.id.yDude);
        yThat = root.findViewById(R.id.yThat);
        yPew = root.findViewById(R.id.yPew);
        yScoop = root.findViewById(R.id.yScoop);

        yFlying = root.findViewById(R.id.yFlying);
        yNikhil = root.findViewById(R.id.yNikhil);
        yGaurav = root.findViewById(R.id.yGaurav);
        yTanee = root.findViewById(R.id.yTanee);

        yLarry.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UC39a9uWnGIDYzi5BMndFmiw";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yBarbell.setOnClickListener(v -> {
            String link = "https://www.youtube.com/user/bartkwan";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yNoel.setOnClickListener(v -> {
            String link = "https://www.youtube.com/user/WanderBoy2010";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yTone.setOnClickListener(v -> {
            String link = "https://www.youtube.com/user/ToneItUpcom";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });


        yDude.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UCRijo3ddMTht_IHyNSNXpNQ";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yThat.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UCSrnmu3W6YXWU_85DKT5arg";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yPew.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UC-lHJZR3Gqxm24_Vd_AJ5Yw";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yScoop.setOnClickListener(v -> {
            String link = "https://www.youtube.com/user/scoopwhoopvideos";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });


        yFlying.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UCNSdjX4ry9fICqeObdZPAZQ";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yNikhil.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UCNn6AaHharXIbkRleXGboiQ";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yGaurav.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UCXsXitjiT_8qPgNEFGPVfBA";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });
        yTanee.setOnClickListener(v -> {
            String link = "https://www.youtube.com/channel/UC70k5W4LvFKxIhhp1fXDBpQ";
            Intent youtube = new Intent(Intent.ACTION_VIEW);
            youtube.setData(Uri.parse(link));
            startActivity(youtube);
        });


        return root;
    }
}