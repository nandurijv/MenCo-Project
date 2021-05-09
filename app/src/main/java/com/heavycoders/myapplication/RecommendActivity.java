package com.heavycoders.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.heavycoders.myapplication.fragments.InstagramFragment;
import com.heavycoders.myapplication.fragments.YoutubeFragment;

public class RecommendActivity extends AppCompatActivity {
    Toolbar toolbar3;
    BottomNavigationView bnv2;
    FrameLayout container2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);

        bnv2 = findViewById(R.id.bnv2);
        toolbar3 = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar3);
        container2 = findViewById(R.id.container2);



        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back_white);
        bnv2.setBackground(null);
        loadFragment(new InstagramFragment());

        bnv2.setOnNavigationItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.instagram:
                    loadFragment(new InstagramFragment());
                    actionBar.setTitle("Instagram Accounts");
                    return true;
                case R.id.youtube:
                    loadFragment(new YoutubeFragment());
                    actionBar.setTitle("YouTube Channels");
                    return true;
            }
            return true;
        });



    }

    public void loadFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container2, fragment);
        transaction.commit();
    }
}