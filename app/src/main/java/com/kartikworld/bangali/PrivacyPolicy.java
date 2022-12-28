package com.kartikworld.bangali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class PrivacyPolicy extends AppCompatActivity {

    //TextView textView;
    Button buttonThumbLoad,buttonGallery ;
    AdView mAdView;
    ProgressBar progressBar;
    public static boolean OnAd=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacy_policy);

        //textView=findViewById(R.id.textView);
        mAdView=findViewById(R.id.adView);
        buttonGallery=findViewById(R.id.buttonGallery);
        buttonThumbLoad=findViewById(R.id.buttonThumbLoad);
        progressBar=findViewById(R.id.progressBar);
        buttonThumbLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrivacyPolicy.this, ThumbLoader.class));
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrivacyPolicy.this, GalleryView.class));
            }
        });
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });


// Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.kartikworld.com/apps/adonof.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        progressBar.setVisibility(View.GONE);

                        if (response.contains("OnAd")) {
                            OnAd=true;
                            mAdView.setVisibility(View.VISIBLE);
                            AdRequest adRequest=new AdRequest.Builder().build();
                            mAdView.loadAd(adRequest);

                        }else {
                            mAdView.setVisibility(View.GONE);
                            OnAd=false;
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}