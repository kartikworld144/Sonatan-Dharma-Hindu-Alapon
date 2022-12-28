package com.kartikworld.bangali;

import static com.kartikworld.bangali.WebBrowser.isNetworkAvailable;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {

    public static boolean OnAd=false;
    AdView mAdView;
    public static int CategoryClicked = -10;
    ImageSlider imageSlider;
    ExpandableHeightGridView mainGrid;
    RelativeLayout rLayRateUs;
    Intent targetActivity;
    ProgressBar progressBar;

    // per app run-- do not show more than 4 fullscreen ad. [[Change it if your want]]
    int fullScreenAdMaxShowCount = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++
        mAdView = findViewById(R.id.adView);
        imageSlider = findViewById(R.id.image_slider);
        mainGrid = findViewById(R.id.mainGrid);
        rLayRateUs = findViewById(R.id.rLayRateUs);
        mAdView.setVisibility(View.GONE);
        progressBar=findViewById(R.id.progressBar);
        //-----------------------------------------------------

        if (!isNetworkAvailable(Home.this)) {
            progressBar.setVisibility(View.GONE);

        } else {

            progressBar.setVisibility(View.VISIBLE);

        }



        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------


        //*************************************************

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });


        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://kartikworlddotcom.000webhostapp.com/apps/adonof.php";

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


        //-------------------------------------------
        //-------------------------------------------

        //*******************************************
        //*******************************************

        createSlider();
        MakeVideoList.createMyAlbums();
        rateUsOnGooglePlay();

        MyAdapter adapter = new MyAdapter();
        mainGrid.setExpanded(true);
        mainGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        }
        //------onCreate (bundle) ENDS here--------



    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public void createSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();
        //imageList.add(new SlideModel(R.drawable.slide_1, null));
        imageList.add(new SlideModel(R.drawable.news, "Daily important videos for you", null));
        imageList.add(new SlideModel(R.drawable.happy_diwali, "Long live your beautiful life", null));
        imageList.add(new SlideModel(R.drawable.lots_candles, "Lots of candles in the dark", null));
        imageList.add(new SlideModel(R.drawable.wat_temple, "Very beautiful old temple", null));
        imageList.add(new SlideModel(R.drawable.temple_singapore, "Holly Temple Singapore", null));
        imageList.add(new SlideModel(R.drawable.revisions, "Daily newspaper reading is a good habit", null));
        imageSlider.setImageList(imageList, ScaleTypes.FIT);


        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int position) {

                if (position == 0) {
                    Toast.makeText(getBaseContext(), "Image 1: Do something", Toast.LENGTH_SHORT).show();
                }

                if (position == 1) {
                    Toast.makeText(getBaseContext(), "Image 2: Do something", Toast.LENGTH_SHORT).show();
                }
                if (position == 2) {
                    Toast.makeText(getBaseContext(), "Image 3: Do something", Toast.LENGTH_SHORT).show();
                }
                if (position == 3) {
                    Toast.makeText(getBaseContext(), "Image 4: Do something", Toast.LENGTH_SHORT).show();
                }
                if (position == 4) {
                    Toast.makeText(getBaseContext(), "Image 5: Do something", Toast.LENGTH_SHORT).show();
                }
                if (position == 5) {
                    Toast.makeText(getBaseContext(), "Image 6: Do something", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    //-------------------------------------------------------------
    //-------------------------------------------------------------


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public MyAdapter() {
            this.inflater = (LayoutInflater) Home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return MakeVideoList.catArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);


            ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            LinearLayout layItem = convertView.findViewById(R.id.layItem);

            HashMap<String, String> mHashMap = MakeVideoList.catArrayList.get(position);
            String category_name = mHashMap.get("category_name");
            String img = mHashMap.get("img");
            String url = mHashMap.get("url");
            String pdfAssetName = mHashMap.get("pdfAssetName");

            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            if (tvTitle != null) tvTitle.setText(category_name);

            if (imgIcon != null && img != null) {
                int drawable = Integer.parseInt(img);
                imgIcon.setImageResource(drawable);
            }
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            Animation animation = AnimationUtils.loadAnimation(Home.this, R.anim.splash_top);
            animation.setStartOffset(position * 80);
            convertView.startAnimation(animation);
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

            if (layItem != null) {

                layItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //We are tracking the category position. So that we can call the intent after ad loads

                        if (url != null && url.contains("BMI")) {

                            //BMI.NAME = "BMI";
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, BMI.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, BMI.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, BMI.class);
                                startActivity(intent);

                            }
                        }
                        //-------------------------------------------------------------------------------
                        else if (url != null && url.contains("ThumbLoader")) {

                            try {

                            if (!isNetworkAvailable(Home.this)) {
                                Intent intent = new Intent(Home.this, ThumbLoader.class);
                                startActivity(intent);

                            } else {

                                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                if (mInterstitialAd == null) startActivity(targetActivity);
                                else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                    startActivity(targetActivity);
                                else mInterstitialAd.show(Home.this);
                                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                Intent intent = new Intent(Home.this, ThumbLoader.class);
                                startActivity(intent);

                            }

                        } catch (Exception e) {
                            Intent intent = new Intent(Home.this, ThumbLoader.class);
                            startActivity(intent);

                        }
                        }
                        //-------------------------------------------------------------------------------
                        else if (url != null && url.contains("MyTube")) {

                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, MyTube.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, MyTube.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, MyTube.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------
                        else if (url != null && url.contains("SMS")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, MainMovieActivity.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, MainMovieActivity.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, MainMovieActivity.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------
                        else if (url != null && url.contains("Popular")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, PopularNews.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, PopularNews.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, PopularNews.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------
                        else if (url != null && url.contains("Weather")) {

                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, WeatherNews.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, WeatherNews.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, WeatherNews.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------
                        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        else if (url != null && url.contains("PanchaTattva")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, PanchaTattva.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, PanchaTattva.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, PanchaTattva.class);
                                startActivity(intent);

                            }
                        }
                        //-------------------------------------------------------------------------------

                        else if (url != null && url.contains("Vow")) {

                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, Vow.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, Vow.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, Vow.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------
                            else if (url != null && url.contains("Ekadashi")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, Ekadashi.class);
                                    startActivity(intent);

                                } else {

                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                    Intent intent = new Intent(Home.this, Ekadashi.class);
                                    startActivity(intent);

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, Ekadashi.class);
                                startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------


                        //-------------------------------------------------------------------------------

                        else if (url != null && url.contains("Database")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, Database.class);
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(Home.this, Database.class);
                                    startActivity(intent);


                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, Database.class);
                                startActivity(intent);

                            }
                        }
                        //-------------------------------------------------------------------------------


                        //-------------------------------------------------------------------------------

                        else if (url != null && url.contains("NoteArrayList")) {
                            try {

                                if (!isNetworkAvailable(Home.this)) {
                                    Intent intent = new Intent(Home.this, NoteArrayList.class);
                                    startActivity(intent);

                                } else {
                                    Intent intent = new Intent(Home.this, NoteArrayList.class);
                                    startActivity(intent);
                                    /*-
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                                    if (mInterstitialAd == null) startActivity(targetActivity);
                                    else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                        startActivity(targetActivity);
                                    else mInterstitialAd.show(Home.this);
                                    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++

                                     */

                                }

                            } catch (Exception e) {
                                Intent intent = new Intent(Home.this, NoteArrayList.class);startActivity(intent);
                            }
                        }
                        //-------------------------------------------------------------------------------


                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        // Check the item is a website link
                        else if (url != null && url.length() > 5) {
                            WebBrowser.WEBSITE_LINK = url;
                            WebBrowser.WEBSITE_TITLE = category_name;
                            //startActivity(new Intent(Home.this, WebBrowser.class));
                            targetActivity = new Intent(Home.this, WebBrowser.class);
                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            if (mInterstitialAd == null) startActivity(targetActivity);
                            else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                startActivity(targetActivity);
                            else mInterstitialAd.show(Home.this);
                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        }

                        // Check the item is a PDF FILE OR NOT
                        else if (pdfAssetName != null && pdfAssetName.length() > 3) {
                            PDFViewer.PDF_ASSET_NAME = pdfAssetName;
                            PDFViewer.PDF_TITLE = category_name;

                            //startActivity(new Intent(Home.this, PDFViewer.class));
                            targetActivity = new Intent(Home.this, PDFViewer.class);
                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            if (mInterstitialAd == null) startActivity(targetActivity);
                            else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                startActivity(targetActivity);
                            else mInterstitialAd.show(Home.this);
                        }
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        // None of the above is true. So the category is video collection type

                        else {
                            CategoryClicked = position;
                            My_Tube_Player.arrayList = MakeVideoList.rootArrayList.get(position);

                            targetActivity = new Intent(Home.this, My_Tube_Player.class);
                            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                            if (mInterstitialAd == null) startActivity(targetActivity);
                            else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                                startActivity(targetActivity);
                            else mInterstitialAd.show(Home.this);
                        }
                    }
                });
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            }


            return convertView;
        }
    }
    //--------------------------------------------------------
    //--------------------------------------------------------

    // BANNER_AD_ method starts here.....

    int BANNER_AD_CLICK_COUNT = 0;

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    private void loadBannerAd() {
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                progressBar.setVisibility(View.GONE);
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


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void rateUsOnGooglePlay() {
        rLayRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anew) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

}