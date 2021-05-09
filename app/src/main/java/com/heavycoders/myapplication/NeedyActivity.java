package com.heavycoders.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heavycoders.myapplication.fragments.BedFragment;
import com.heavycoders.myapplication.fragments.FoodFragment;
import com.heavycoders.myapplication.fragments.OxygenFragment;
import com.heavycoders.myapplication.fragments.PlasmaFragment;

public class NeedyActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    Toolbar toolbar;
    private FrameLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_needy);

        bnv = findViewById(R.id.bnv);
        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);

        setSupportActionBar(toolbar);
        bnv.setBackground(null);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_white);

        loadFragment(new OxygenFragment());

        bnv.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.oxygen:
                    loadFragment(new OxygenFragment());
                    toolbar.setTitle("Oxygen Supply");
                    return true;
                case R.id.plasma:
                    loadFragment(new PlasmaFragment());
                    toolbar.setTitle("Plasma Assistance");
                    return true;
                case R.id.food:
                    loadFragment(new FoodFragment());
                    toolbar.setTitle("Food Service");
                    return true;
                case R.id.hospital:
                    loadFragment(new BedFragment());
                    toolbar.setTitle("Hospital Beds");
                    return true;
            }
            return true;
        });
    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.commit();
    }
}