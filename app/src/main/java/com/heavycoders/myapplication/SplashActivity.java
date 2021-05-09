package com.heavycoders.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SplashActivity extends AppCompatActivity {
    private TextView textView, quote;
    private LottieAnimationView main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.textView);
        quote = findViewById(R.id.quote);
        main = findViewById(R.id.lottieAnimationView);
        main.animate().translationYBy(2000f).setDuration(500);
        textView.animate().translationYBy(-2000f).setDuration(500);
        quote.animate().alpha(1f).setDuration(1000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 5000);

        String url = "https://api.quotable.io/random";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, s -> {
            try {
                JSONObject response = new JSONObject(s);
                String randomQuote = response.getString("content");
                quote.setText(randomQuote);
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }, error -> Log.i("error", "no internet connection"));
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);


    }
}