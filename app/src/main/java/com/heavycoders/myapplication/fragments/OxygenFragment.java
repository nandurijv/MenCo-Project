package com.heavycoders.myapplication.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.heavycoders.myapplication.R;
import com.heavycoders.myapplication.adapters.OxygenAdapter;
import com.heavycoders.myapplication.models.Model;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OxygenFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OxygenFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView oxygenRecyclerView;
    public OxygenFragment() {

    }


    public static OxygenFragment newInstance(String param1, String param2) {
        OxygenFragment fragment = new OxygenFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_oxygen, container, false);

        Bundle bd = getActivity().getIntent().getExtras();
        String state = bd.getString("state");
        String city = bd.getString("city");
        String name = bd.getString("name");

        oxygenRecyclerView = root.findViewById(R.id.oxygenRecyclerView);
        LottieAnimationView noView = root.findViewById(R.id.noData);
        TextView noText = root.findViewById(R.id.noText);
        oxygenRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ArrayList<Model> list = new ArrayList<>();


        OxygenAdapter oxygenAdapter = new OxygenAdapter(list, getContext());
        oxygenRecyclerView.setAdapter(oxygenAdapter);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("oxygensupply").child(state).child(city);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Model model = dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                if(list.isEmpty()){
                    noView.setVisibility(View.VISIBLE);
                    noText.setVisibility(View.VISIBLE);

                }
                oxygenAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return root;
    }
}