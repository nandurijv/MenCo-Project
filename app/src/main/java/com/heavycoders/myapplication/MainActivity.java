package com.heavycoders.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.solver.state.State;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heavycoders.myapplication.adapters.StateAdapter;
import com.heavycoders.myapplication.models.StateModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements LocationListener {
    private FirebaseAuth mAuth;
    private TextView greeting, userName, slogan;
    private DatabaseReference db;
    private CardView volunteer, resources, recommendation;
    private EditText stateName;
    private LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;
    private ImageView profile;
    Geocoder geocoder;
    List<Address> addresses;
    RecyclerView allStatesRecyclerView;
    ConstraintLayout top;
    TextInputLayout location;
    TextView servicesText;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        greeting = findViewById(R.id.greeting);
        userName = findViewById(R.id.userName);
        slogan = findViewById(R.id.slogan);
        stateName = findViewById(R.id.stateName);
        volunteer = findViewById(R.id.volunteer);
        resources = findViewById(R.id.resources);
        recommendation = findViewById(R.id.recommendation);
        profile = findViewById(R.id.profile);
        allStatesRecyclerView = findViewById(R.id.allStatesRecyclerView);
        top = findViewById(R.id.top);
        location = findViewById(R.id.location);
        servicesText = findViewById(R.id.servicesText);


        recommendation.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, RecommendActivity.class));
        });

        profile.setOnClickListener(v -> {
            mAuth.signOut();
            Toast.makeText(this, "Successfully Logged out.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager
                .PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        } else {
            try {
                Location location = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                onLocationChanged(location);
            } catch (Exception e) {
                Toast.makeText(this, "Location not enabled.", Toast.LENGTH_SHORT).show();
            }
        }


        volunteer.setOnClickListener(v -> {
            if(stateName.getText().toString().isEmpty()){
                Toast.makeText(this, "Location cannot be empty, Please Wait...", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, VolunteerActivity.class);
                intent.putExtra("state", addresses.get(0).getAdminArea());
                intent.putExtra("city", addresses.get(0).getLocality());
                intent.putExtra("name", userName.getText().toString());
                startActivity(intent);
            }
        });

        resources.setOnClickListener(v -> {
            if(stateName.getText().toString().isEmpty()){
                Toast.makeText(this, "Location cannot be empty, Please Wait...", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, NeedyActivity.class);
                intent.putExtra("state", addresses.get(0).getAdminArea());
                intent.putExtra("city", addresses.get(0).getLocality());
                intent.putExtra("name", userName.getText().toString());
                startActivity(intent);
            }
        });

        // ALL STATES DATA HERE //

        ArrayList<StateModel> stateList = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        allStatesRecyclerView.setLayoutManager(linearLayoutManager);
        StateAdapter stateAdapter = new StateAdapter(MainActivity.this,stateList);
        allStatesRecyclerView.setAdapter(stateAdapter);

        String url = "https://api.covid19india.org/data.json";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, s -> {
            try {
                JSONObject object = new JSONObject(s);
                String list = object.getString("statewise");
                JSONArray jsonArray = new JSONArray(list);
                for(int i = 0;i<jsonArray.length();i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String stateName = jsonObject.getString("state");
                    Log.i("state", jsonObject.getString("state"));
                    String active = jsonObject.getString("active");
                    String deaths = jsonObject.getString("deaths");
                    String recovered = jsonObject.getString("recovered");
                    String confirmed = jsonObject.getString("confirmed");
                    String lastupdate = jsonObject.getString("lastupdatedtime");

                    StateModel stateModel = new StateModel(stateName,active,confirmed,deaths,recovered,lastupdate);
                    stateList.add(stateModel);


                }
                stateAdapter.notifyDataSetChanged();


            } catch (JSONException e) {
                e.printStackTrace();

            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error", "no internet connection");
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


        // ANIMATIONS//
        top.animate().translationYBy(2000f).setDuration(1000);
        allStatesRecyclerView.animate().translationXBy(2000f).setDuration(500);
        slogan.animate().translationXBy(2000f).setDuration(600);
        location.animate().translationXBy(2000f).setDuration(700);
        servicesText.animate().translationXBy(2000f).setDuration(800);
        recommendation.animate().translationXBy(2000f).setDuration(900);
        volunteer.animate().translationXBy(2000f).setDuration(1000);
        resources.animate().translationXBy(2000f).setDuration(1100);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        } else {
            Date date = new Date();
            int time = date.getHours();
            if (time >= 0 && time <= 12) {
                greeting.setText("Good Morning,");
            } else if (time > 12 && time <= 16) {
                greeting.setText("Good Afternoon,");
            } else if (time > 16 && time <= 20) {
                greeting.setText("Good Evening,");
            } else if (time > 20 && time <= 24) {
                greeting.setText("Good Night,");
            }
            Task<DataSnapshot> name = db.child("users").child(mAuth.getCurrentUser().getUid()).child("name").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (task.isSuccessful()) {
                        userName.setText(task.getResult().getValue().toString());
                    }
                }
            });
        }
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        Log.i("lat", latitude + "");
        Log.i("long", longitude + "");
        geocoder = new Geocoder(this, Locale.getDefault());
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Log.i("state", addresses.get(0).getAdminArea().toString());
            Log.i("city", addresses.get(0).getLocality());
            stateName.setText(addresses.get(0).getLocality() + ", " + addresses.get(0).getAdminArea());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}