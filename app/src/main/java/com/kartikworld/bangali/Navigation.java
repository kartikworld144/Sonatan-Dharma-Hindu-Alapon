package com.kartikworld.bangali;

import static com.kartikworld.bangali.WebBrowser.isNetworkAvailable;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Navigation extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    Button buttonMore,buttonNews,buttonSms,buttonMyTube,buttonGita;
    AdView mAdView;
    TextView appName, marquee;
    public static boolean OnAd=false;
    LottieAnimationView animationView;
    ImageSlider imageSlider;
    Animation Splash_top,Splash_bottom;
    RelativeLayout rLayRateUs;
    Intent targetActivity;
    PreferenceManager preferenceManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static Bitmap PRO_IMG_BITMAP=null;

    // per app run-- do not show more than 4 fullscreen ad. [[Change it if your want]]
    int fullScreenAdMaxShowCount = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for full screen
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.navigation);

        buttonGita = findViewById(R.id.buttonGrid);
        appName = findViewById(R.id.appName);
        mAdView=findViewById(R.id.adView);
        imageSlider = findViewById(R.id.image_slider);
        animationView = findViewById(R.id.animationView);


        //***************************************************
        //***************************************************

        //+++++++++++++++++++++++++++++++++++++++++++++++++++

        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();


        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        View headerView = navigationView.getHeaderView(0);
        TextView tvName = (TextView) headerView.findViewById(R.id.tvName);
        TextView tvEmail = (TextView) headerView.findViewById(R.id.tvEmail);
        String name = sharedPreferences.getString("saved_Name","Name : Xyz");
        String email = sharedPreferences.getString("saved_Email","Email: xyz@gmail.com");
        tvName.setText(name);
        tvEmail.setText(email);

        LinearLayout linearLayout = (LinearLayout) headerView.findViewById(R.id.linearLayout);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Navigation.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });




        tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Navigation.this, "SHOW", Toast.LENGTH_LONG).show();

            }
        });

        CircleImageView  imgProfile = (CircleImageView) headerView.findViewById(R.id.imgProfile);
        //if(PRO_IMG_BITMAP!=null) imgProfile.setImageBitmap(PRO_IMG_BITMAP);
        /*Glide
                .with(Navigation.this)
                //.load("https://ua-travel.info/fotos/galery/152/1754.jpg")
                .load(PRO_IMG_BITMAP)
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_person)
                .into(imgProfile);

         */

        preferenceManager=PreferenceManager.getInstance(this);

        String previouslyEncodedImage = preferenceManager.getString("image_data");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imgProfile.setImageBitmap(bitmap);
        }
        //***************************************************
        //***************************************************


        Splash_top = AnimationUtils.loadAnimation(this, R.anim.splash_top);
        Splash_bottom = AnimationUtils.loadAnimation(this, R.anim.splash_bottom);

        animationView.setAnimation(Splash_top);
        appName.setAnimation(Splash_bottom);

        buttonMore=findViewById(R.id.buttonMore);
        buttonNews=findViewById(R.id.buttonNews);
        buttonSms=findViewById(R.id.buttonSms);
        buttonMyTube=findViewById(R.id.buttonMyTube);
        rLayRateUs = findViewById(R.id.rLayRateUs);


        toolbar=findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);
        drawerLayout=findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        //+++++++++++++++++++++++++++++++++++++++++++++++++++
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        buttonMore.startAnimation(shake);
        buttonNews.startAnimation(shake);
        buttonMyTube.startAnimation(shake);
        imageSlider.startAnimation(shake);
        rLayRateUs.startAnimation(shake);
        buttonGita.startAnimation(shake);
        //---------------------------------------------------


        //*************************************************
        marquee = findViewById(R.id.marquee);
        marquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marquee.setText("সনাতন ধর্ম হিন্দু আলাপনে আপনাকে স্বাগতম। যারা তীর্থযাত্রা করতে পারেন না তাদের জন্য অ্যাপটি বিশেষভাবে কার্যকর। সনাতন ধর্ম হিন্দু আলাপন অ্যাপটি হিন্দু ধর্মের তথ্যের বৃহত্তর আধারে দ্রুত অ্যাক্সেস দেয়। অ্যাপটি ভক্তদের ধর্ম-কর্ম, পূজা, ব্রতকথা, শ্রাদ্ধ (ভোজ), বৈষ্ণব ও গৌ সেবা সম্পর্কে জানতে সাহায্য করবে।");
        marquee.setSelected(true);
        marquee.setSingleLine(true);
        marquee.append("");
        marquee.setVisibility(View.VISIBLE);
        //*************************************************


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });



        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        buttonMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation.this,Home.class));
                //marquee.setVisibility(View.GONE);

            }
        });

        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation.this, NewsHome.class));
                //marquee.setVisibility(View.GONE);
            }

        });

        buttonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Navigation.this, SMS.class));
                //marquee.setVisibility(View.GONE);
            }

        });

        buttonMyTube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //marquee.setVisibility(View.GONE);
                startActivity(new Intent(Navigation.this, MyTube.class));
            }
        });
        buttonGita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //marquee.setVisibility(View.INVISIBLE);
                try {

                    if (!isNetworkAvailable(Navigation.this)) {
                        Intent intent = new Intent(Navigation.this, BhagavadGita.class);
                        startActivity(intent);

                    } else {

                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        if (mInterstitialAd == null) startActivity(targetActivity);
                        else if (FULLSCREEN_AD_LOAD_COUNT >= fullScreenAdMaxShowCount)
                            startActivity(targetActivity);
                        else mInterstitialAd.show(Navigation.this);
                        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                        Intent intent = new Intent(Navigation.this, BhagavadGita.class);
                        startActivity(intent);
                    }

                } catch (Exception e) {
                    Intent intent = new Intent(Navigation.this, BhagavadGita.class);
                    startActivity(intent);
                }
            }
        });

        createSlider();
        rateUsOnGooglePlay();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(@NonNull InitializationStatus initializationStatus) {

            }
        });

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

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
                            //---------------------------------------------
                            if (getString(R.string.Admob).contains("ON")) {
                                initAdmobAd();
                                loadBannerAd();
                                loadFullscreenAd();
                            }
                            //---------------------------------------------
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


    }


    // ================================================================
    private void createSlider() {

        ArrayList<SlideModel> imageList = new ArrayList<>();
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
    // ================================================================
    // ================================================================

    @Override
    public void onBackPressed() {
        //marquee.setVisibility(View.GONE);
        new AlertDialog.Builder(Navigation.this)

                .setTitle("Please Confirm To Exit")
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Navigation.super.onBackPressed();
                    }
                })

                .setNeutralButton("Skip", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    private void rateUsOnGooglePlay() {
        rLayRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //marquee.setVisibility(View.GONE);
                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anew) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });

}


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.navi_home){
            Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show();
        }
        else if (item.getItemId()==R.id.navi_rate){
            final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anew) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
            Toast.makeText(this,"Rate us",Toast.LENGTH_SHORT).show();
        }


        else if (item.getItemId()==R.id.navi_settings){
            startActivity(new Intent(Navigation.this, Settings.class));
        }

        else if (item.getItemId()==R.id.navi_share){
            Toast.makeText(this,"Share",Toast.LENGTH_SHORT).show();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Download this fantastic app & share with your friends\n\nসনাতন ধর্ম হিন্দু আলাপন \nhttps://play.google.com/store/apps/details?id="+getPackageName());
            //sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        }

        else if (item.getItemId()==R.id.navi_facebook){
            WebBrowser.WEBSITE_LINK="https://www.facebook.com/profile.php?id=100015893013128";
            Intent intent1 = new Intent(Navigation.this,WebBrowser.class);
            startActivity(intent1);
            /*Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100068389460128"));
            if (Navigation.this.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
                intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100015893013128"));
            }
            Navigation.this.startActivity(intent);
            Toast.makeText(this,"Facebook",Toast.LENGTH_SHORT).show(); */
        }



        else if (item.getItemId()==R.id.navi_feedback){
            startActivity(new Intent(Navigation.this, Feedback.class));
            Toast.makeText(this,"Write Your Feedback",Toast.LENGTH_SHORT).show();
        }

        else if (item.getItemId()==R.id.navi_whatsapp){
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setData(Uri.parse("https://chat.whatsapp.com/JTD2UhNQwfOATM60DmqZRb"));
            if (Navigation.this.getPackageManager().queryIntentActivities(intent, 0).size() == 0) {
                intent.setPackage("com.whatsapp");
            }
            Navigation.this.startActivity(intent);
            Toast.makeText(this,"Login & Join Whatsapp",Toast.LENGTH_SHORT).show();
        }

        else if (item.getItemId()==R.id.navi_sign_out){


            editor.clear();
            editor.commit();
            FirebaseAuth.getInstance().signOut();
            SharePref.RemoveSharePreference(this);
            Toast.makeText(Navigation.this, "Sign_Out", Toast.LENGTH_SHORT).show();
            //tvResult.clearComposingText();
            startActivity(new Intent(Navigation.this, LoginActivity.class));
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;

    }

    //--------------------------------------------------------

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // loadBannerAd method starts here.....
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
    // loadBannerAd method ENDS  here.....
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
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


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

}