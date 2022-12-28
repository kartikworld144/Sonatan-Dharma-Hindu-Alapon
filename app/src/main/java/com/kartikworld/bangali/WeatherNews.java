package com.kartikworld.bangali;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class WeatherNews extends AppCompatActivity {
    LottieAnimationView progressBar;
    ListView listView;
    LinearLayout layNoNet;
    SwipeRefreshLayout swipeRefreshLayout;

    ArrayList<HashMap<String,String> >arrayList=new ArrayList<>();
    HashMap <String,String>hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_list);

        listView = findViewById(R.id.listView);
        layNoNet = findViewById(R.id.layNoNet);
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);
        progressBar=findViewById(R.id.progressBar);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);


        //+++++++++++++++++++++++++++++++++++++++++++++++
        if(!isNetworkAvailable(WeatherNews.this)){
            listView.setVisibility(View.GONE);
            layNoNet.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            layNoNet.setVisibility(View.GONE);
        }
        //-------------------------------------------------


        //+++++++++++++++++++++++++++++++++++++++++++++++

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                swipeRefreshLayout.setRefreshing(false);

            }
        });

        //-----------------------------------------------
        Button tryAgain = (Button) findViewById(R.id.tryAgain);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(WeatherNews.this, WeatherNews.class);
                startActivity(intent);
                finish();
            }
        });

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                // add toolbar
                Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("জনপ্রিয় আবহাওয়ার খবর");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------


        String url = "https://kartikworlddotcom.000webhostapp.com/apps/weather.json";

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
                listView.setAdapter(myAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherNews.this);
        requestQueue.add(arrayRequest);
    }
    //********************************************
    //********************************************

        class MyAdapter extends BaseAdapter {

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

                LayoutInflater layoutInflater=getLayoutInflater();
                View myView=layoutInflater.inflate(R.layout.news_item,null);

                //********************************************
                Animation animation = AnimationUtils.loadAnimation(WeatherNews.this, R.anim.splash_top);
                animation.setStartOffset(i*10);
                myView.startAnimation(animation);
                //********************************************

            //********************************************
            ImageView news_imageView=myView.findViewById(R.id.news_imageView);
            TextView newsCat=myView.findViewById(R.id.newsCat);
            TextView newsTitle=myView.findViewById(R.id.newsTitle);
            TextView newsDes=myView.findViewById(R.id.newsDes);
            LinearLayout linearLayout=myView.findViewById(R.id.linearLayout);
            //********************************************
            //********************************************
                HashMap<String,String>hashMap=arrayList.get(i);
                String cat=hashMap.get("cat");
                String image_url=hashMap.get("image_url");
                String title=hashMap.get("title");
                String des=hashMap.get("des");

                Picasso.get().load(image_url)
                        .placeholder(R.drawable.img)
                        .into(news_imageView);
                newsTitle.setText(title);
                newsDes.setText(des);
                newsCat.setText(cat);

                //********************************************
            //********************************************
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            newsCat.setBackgroundColor(color);
            //********************************************
            //********************************************
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    NewsDetails.TITLE=title;
                    NewsDetails.DESCRIPTION=des;
                    Bitmap bitmap=((BitmapDrawable) news_imageView.getDrawable()).getBitmap();
                    NewsDetails.MY_BITMAP=bitmap;



                    startActivity(new Intent(WeatherNews.this, NewsDetails.class));
                }
            });

            return myView;
        }
    }

    //********************************************




    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }
    //----------------------------------------------------------



    //********************************************
    //********************************************


}