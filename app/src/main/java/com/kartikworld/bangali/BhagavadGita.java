package com.kartikworld.bangali;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
//import com.appolica.flubber.Flubber;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BhagavadGita extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    GridView gridView;
    LinearLayout layNoNet;
    int count = 0;
    LottieAnimationView progressBar;
    ArrayList<HashMap<String,String> >arrayList=new ArrayList<>();
    HashMap <String,String>hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bhagavad_gita);


        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        gridView=findViewById(R.id.gridView);
        layNoNet = findViewById(R.id.layNoNet);
        progressBar=findViewById(R.id.progressBar);

        Auto_refresh();


        MyAdapter adapter=new MyAdapter();
        gridView.setAdapter(adapter);



        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("শ্রীমদ্ভগবদ্গীতা যথাযথ বাংলা");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        Button tryAgain = (Button) findViewById(R.id.tryAgain);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(BhagavadGita.this, BhagavadGita.class);
                startActivity(intent);
                finish();

            }
        });

        //+++++++++++++++++++++++++++++++++++++++++++++++
        if(!isNetworkAvailable(BhagavadGita.this)){
            gridView.setVisibility(View.GONE);
            layNoNet.setVisibility(View.VISIBLE);
        }else{
            gridView.setVisibility(View.VISIBLE);
            layNoNet.setVisibility(View.GONE);
        }
        //-----------------------------------------------

        //+++++++++++++++++++++++++++++++++++++++++++++++

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                Auto_refresh();
                swipeRefreshLayout.setRefreshing(false);

            }
        });

        //-----------------------------------------------



        String url = "https://kartikworlddotcom.000webhostapp.com/apps/gita.json";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                try {
                    for (int x = 0; x < response.length(); x++) {
                        JSONObject jsonObject = response.getJSONObject(x);
                        String title = jsonObject.getString("title");
                        String des = jsonObject.getString("des");
                        String cat = jsonObject.getString("cat");
                        String image_url = jsonObject.getString("image_url");
                        hashMap = new HashMap<>();
                        hashMap.put("title", title);
                        hashMap.put("image_url", image_url);
                        hashMap.put("cat", cat);
                        hashMap.put("des", des);
                        arrayList.add(hashMap);


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                MyAdapter myAdapter = new MyAdapter();
                gridView.setAdapter(myAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(BhagavadGita.this);
        requestQueue.add(arrayRequest);
    }

    private void Auto_refresh() {
        count++;
        refresh(1000);

    }

    private void refresh(int milliseconds) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Auto_refresh();
            }
        };
        handler.postDelayed(runnable, milliseconds);

    }
    //********************************************
    //********************************************


    public class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.gita_grid_item, null);


            ImageView news_imageView = myView.findViewById(R.id.news_imageView);
            TextView newsCat = myView.findViewById(R.id.newsCat);
            TextView newsTitle = myView.findViewById(R.id.newsTitle);
            TextView newsDes = myView.findViewById(R.id.newsDes);
            LinearLayout linearLayout = myView.findViewById(R.id.linearLayout);

            //********************************************
            //********************************************
            HashMap<String, String> hashMap = arrayList.get(i);
            String cat = hashMap.get("cat");
            String image_url = hashMap.get("image_url");
            String title = hashMap.get("title");
            String des = hashMap.get("des");

            Picasso.get().load(image_url)
                    .placeholder(R.drawable.gita)
                    .into(news_imageView);
            newsTitle.setText(title);
            newsDes.setText(des);
            newsCat.setText(cat);

            //********************************************
            Animation animation = AnimationUtils.loadAnimation(BhagavadGita.this, R.anim.splash_top);
            animation.setStartOffset(i*10);
            myView.startAnimation(animation);
            //********************************************

            //********************************************
            //********************************************
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            newsCat.setBackgroundColor(color);
            Random rnd2 = new Random();
            int color2 = Color.argb(254, rnd2.nextInt(257), rnd2.nextInt(258), rnd2.nextInt(259));
            newsTitle.setBackgroundColor(color2);
            //********************************************
            //********************************************
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    GitaDetails.TITLE = title;
                    GitaDetails.DESCRIPTION = des;
                    Bitmap bitmap = ((BitmapDrawable) news_imageView.getDrawable()).getBitmap();
                    GitaDetails.GITA_BITMAP = bitmap;

                    /*Flubber.with()
                            .animation(Flubber.AnimationPreset.ROTATION)
                            .repeatCount(1)
                            .duration(1000)
                            .createFor(view)
                            .start();

                     */

                    startActivity(new Intent(BhagavadGita.this, GitaDetails.class));
                }
            });

            return myView;
        }
    }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;

    }
    //----------------------------------------------------------
}

//********************************************
//********************************************
//********************************************