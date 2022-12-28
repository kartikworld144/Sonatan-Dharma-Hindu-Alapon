package com.kartikworld.bangali;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.TextView;

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

public class Vow extends AppCompatActivity {


    LottieAnimationView progressBar;
    ListView listView;
    LinearLayout layNoNet;
    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
    HashMap <String,String>hashMap=new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vow);

        progressBar = findViewById(R.id.progressBar);
        listView = findViewById(R.id.listView);
        layNoNet = findViewById(R.id.layNoNet);
        MyAdapter myAdapter=new MyAdapter();
        listView.setAdapter(myAdapter);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("মন্ত্র ও ব্রত পালনের বিধিসমূহ");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        //+++++++++++++++++++++++++++++++++++++++++++++++
        if(!isNetworkAvailable(Vow.this)){
            listView.setVisibility(View.GONE);
            layNoNet.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            layNoNet.setVisibility(View.GONE);
        }
        //-----------------------------------------------
        progressBar.setVisibility(View.VISIBLE);

        String url = "https://kartikworlddotcom.000webhostapp.com/apps/vow.json";

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
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(Vow.this);
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

            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.vow_item, null);


            //********************************************
            Animation animation = AnimationUtils.loadAnimation(Vow.this, R.anim.kartik_anim);
            animation.setStartOffset(i * 20L);
            myView.startAnimation(animation);
            //********************************************


            //********************************************
            ImageView news_imageView = myView.findViewById(R.id.news_imageView);
            TextView newsTitle = myView.findViewById(R.id.newsTitle);
            TextView newsDes = myView.findViewById(R.id.newsDes);
            Button buttonDetails = myView.findViewById(R.id.buttonDetails);
            //********************************************

            //********************************************
            HashMap<String, String> hashMap = arrayList.get(i);
            //String cat = hashMap.get("cat");
            String image_url = hashMap.get("image_url");
            String title = hashMap.get("title");
            String des = hashMap.get("des");
            //********************************************

            //********************************************
            Picasso.get().load(image_url)
                    .placeholder(R.drawable.lordkrishnadestroyedkashi)
                    .into(news_imageView);
            newsTitle.setText(title);
            newsDes.setText(des);


            //********************************************

            //********************************************
            //********************************************
            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            buttonDetails.setBackgroundColor(color);
            //********************************************
            //********************************************
            buttonDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    NewsDetails.TITLE = title;
                    NewsDetails.DESCRIPTION=des;
                    Bitmap bitmap=((BitmapDrawable) news_imageView.getDrawable()).getBitmap();
                    NewsDetails.MY_BITMAP=bitmap;



                    startActivity(new Intent(Vow.this, NewsDetails.class));
                }


            });
            //********************************************


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