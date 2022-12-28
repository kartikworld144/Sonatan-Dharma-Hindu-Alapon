package com.kartikworld.bangali;

import static com.kartikworld.bangali.WebBrowser.isNetworkAvailable;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import java.util.Arrays;
import java.util.List;


public class NewsHome extends AppCompatActivity {

    LinearLayout RootLayout;
    Button buttonViral,buttonBD,buttonIN,buttonPopular,buttonWeather;
    LayoutInflater layoutInflater;
    ImageView myImage,bangladeshImage,indiaImage;
    AdView mAdView;
    Intent targetActivity;
    public static boolean OnAd=false;

    // per app run-- do not show more than 4 fullscreen ad. [[Change it if your want]]
    int fullScreenAdMaxShowCount = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_home);

        mAdView = findViewById(R.id.adView);
        RootLayout=findViewById(R.id.RootLayout);
        buttonViral=findViewById(R.id.buttonViral);
        buttonBD=findViewById(R.id.buttonBD);
        buttonIN=findViewById(R.id.buttonIN);
        buttonPopular=findViewById(R.id.buttonPopular);
        buttonWeather=findViewById(R.id.buttonWeather);

        layoutInflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View myView= layoutInflater.inflate(R.layout.kartik,RootLayout);
        myImage=myView.findViewById(R.id.myImage);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        buttonViral.startAnimation(shake);
        buttonBD.startAnimation(shake);
        buttonIN.startAnimation(shake);
        buttonPopular.startAnimation(shake);
        buttonWeather.startAnimation(shake);
        //---------------------------------------------------

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://www.kartikworld.com/apps/adonof.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        if (response.contains("OnAd")) {
                            //+++++++++++++++++++++++
                            if (getString(R.string.Admob).contains("ON")) {
                                initAdmobAd();
                                loadBannerAd();
                                loadFullscreenAd();
                            }
                            //+++++++++++++++++++++++
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




    // ================================================================
    // ================================================================

        buttonViral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /*Flubber.with()
                        .animation(Flubber.AnimationPreset.ROTATION)
                        .repeatCount(1)
                        .duration(1000)
                        .createFor(view)
                        .start(); */
                try {

                    if (!isNetworkAvailable(NewsHome.this)) {
                        startActivity(new Intent(NewsHome.this, ViralNews.class));

                    } else {

                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        if (mInterstitialAd == null) startActivity(targetActivity);
                        else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                            startActivity(targetActivity);
                        else mInterstitialAd.show(NewsHome.this);
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        startActivity(new Intent(NewsHome.this, ViralNews.class));

                    }

                } catch (Exception e) {
                    startActivity(new Intent(NewsHome.this, ViralNews.class));

                }
            }

        });


        buttonBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                RootLayout.removeAllViews();
                View bdView= layoutInflater.inflate(R.layout.bangladesh,RootLayout);
                bangladeshImage=bdView.findViewById(R.id.bangladeshImage);
            }

        });
        buttonIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                RootLayout.removeAllViews();
                View inView=layoutInflater.inflate(R.layout.india,RootLayout);
                indiaImage=inView.findViewById(R.id.indiaImage);



            }
        });

        buttonPopular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {

                    if (!isNetworkAvailable(NewsHome.this)) {
                        startActivity(new Intent(NewsHome.this, PopularNews.class));

                    } else {

                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        if (mInterstitialAd == null) startActivity(targetActivity);
                        else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                            startActivity(targetActivity);
                        else mInterstitialAd.show(NewsHome.this);
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        startActivity(new Intent(NewsHome.this, PopularNews.class));

                    }

                } catch (Exception e) {
                    startActivity(new Intent(NewsHome.this, PopularNews.class));

                }
            }
        });

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {

                    if (!isNetworkAvailable(NewsHome.this)) {
                        startActivity(new Intent(NewsHome.this, WeatherNews.class));

                    } else {

                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        if (mInterstitialAd == null) startActivity(targetActivity);
                        else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                            startActivity(targetActivity);
                        else mInterstitialAd.show(NewsHome.this);
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        startActivity(new Intent(NewsHome.this, WeatherNews.class));

                    }

                } catch (Exception e) {
                    startActivity(new Intent(NewsHome.this, WeatherNews.class));

                }

            }
        });


        }

    // loadFullscreenAd method starts here.....
    InterstitialAd mInterstitialAd;
    int FULLSCREEN_AD_LOAD_COUNT = 0;

    private void loadFullscreenAd() {

        //Requesting for a fullscreen Ad
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this, getString(R.string.admob_INTERSTITIAL_UNIT_ID), adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;

                //Fullscreen callback || Requesting again when an ad is shown already
                mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                    @Override
                    public void onAdDismissedFullScreenContent() {
                        // Called when fullscreen content is dismissed.
                        //User dismissed the previous ad. So we are requesting a new ad here
                        FULLSCREEN_AD_LOAD_COUNT++;
                        loadFullscreenAd();
                        Log.d("FULLSCREEN_AD", "" + FULLSCREEN_AD_LOAD_COUNT);

                        if (targetActivity != null) startActivity(targetActivity);

                    }

                }); // FullScreen Callback Ends here


            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }

        });

    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // BANNER_AD_ method starts here.....
    int BANNER_AD_CLICK_COUNT = 0;
    private void loadBannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                if (BANNER_AD_CLICK_COUNT >= 3) {
                    if (mAdView != null) mAdView.setVisibility(View.GONE);
                } else {
                    if (mAdView != null) mAdView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
                BANNER_AD_CLICK_COUNT++;

                if (BANNER_AD_CLICK_COUNT >= 3) {
                    if (mAdView != null) mAdView.setVisibility(View.GONE);
                }

            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // loadFullscreenAd method ENDS  here.....


    private void initAdmobAd() {

        if (getString(R.string.device_id).length() > 12) {
            //Adding your device id -- to avoid invalid activity from your device
            List<String> testDeviceIds = Arrays.asList(getString(R.string.device_id));
            RequestConfiguration configuration =
                    new RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build();
            MobileAds.setRequestConfiguration(configuration);
        }


        //Init Admob Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

    }

}
