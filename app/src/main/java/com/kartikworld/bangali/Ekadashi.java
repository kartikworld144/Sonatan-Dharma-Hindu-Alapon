package com.kartikworld.bangali;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class Ekadashi extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    Button buttonSave;
    LinearLayout layNoNet;
    ProgressBar progressBar;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    ArrayList<HashMap<String,String>> arrayList=new ArrayList<>();
        HashMap <String,String>hashMap=new HashMap<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.ekadashi);
            //********************************************
            progressBar = findViewById(R.id.progressBar);
            swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
            listView = findViewById(R.id.listView);
            buttonSave = findViewById(R.id.buttonSave);
            layNoNet = findViewById(R.id.layNoNet);
            MyAdapter myAdapter=new MyAdapter();
            listView.setAdapter(myAdapter);
            MyAdapter myAdapter1=new MyAdapter();
            listView.setAdapter(myAdapter1);

            Button tryAgain = (Button) findViewById(R.id.tryAgain);

            tryAgain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(Ekadashi.this, Ekadashi.class);
                    startActivity(intent);
                    finish();
                }
            });
            //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
            // add toolbar
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("একাদশী তালিকা ২০২৩");

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
            if(!isNetworkAvailable(Ekadashi.this)){
                listView.setVisibility(View.GONE);
                layNoNet.setVisibility(View.VISIBLE);
                buttonSave.setVisibility(View.GONE);
            }else{
                listView.setVisibility(View.VISIBLE);
                layNoNet.setVisibility(View.GONE);
                buttonSave.setVisibility(View.VISIBLE);

            }
            //-----------------------------------------------

            //+++++++++++++++++++++++++++++++++++++++++++++++
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {

                    swipeRefreshLayout.setRefreshing(false);

                }
            });
            //-----------------------------------------------

            String url = "https://kartikworlddotcom.000webhostapp.com/apps/ekadoshi.json";

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
            RequestQueue requestQueue = Volley.newRequestQueue(Ekadashi.this);
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
                View myView=layoutInflater.inflate(R.layout.ekadashi_item,null);



                //********************************************
                Animation animation = AnimationUtils.loadAnimation(Ekadashi.this, R.anim.splash_top);
                animation.setStartOffset(i* 20L);
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
                //********************************************
                sharedPreferences = getSharedPreferences(""+getString(R.string.app_name),MODE_PRIVATE);
                editor=sharedPreferences.edit();

                //********************************************
                Picasso.get().load(image_url)
                        .placeholder(R.drawable.lordkrishnadestroyedkashi)
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

                        NewsDetails.TITLE = title;
                        NewsDetails.DESCRIPTION=des;
                        Bitmap bitmap=((BitmapDrawable) news_imageView.getDrawable()).getBitmap();
                        NewsDetails.MY_BITMAP=bitmap;

                        String title=hashMap.get("title");
                        String des=hashMap.get("des");
                        editor.putString("title",""+title);
                        editor.putString("des",""+des);

                        editor.apply();
/*
                        Flubber.with()
                                .animation(Flubber.AnimationPreset.ROTATION)
                                .repeatCount(1)
                                .duration(1000)
                                .createFor(view)
                                .start();


 */
                        startActivity(new Intent(Ekadashi.this, NewsDetails.class));
                    }


                });

                buttonSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        String title = sharedPreferences.getString("title", "Default Value");
                        String des = sharedPreferences.getString("des", "Default Value");


                        NewsDetails.TITLE=title;
                        NewsDetails.DESCRIPTION=des;
                        Bitmap bitmap=((BitmapDrawable) news_imageView.getDrawable()).getBitmap();
                        NewsDetails.MY_BITMAP=bitmap;



                        startActivity(new Intent(Ekadashi.this, NewsDetails.class));

                    }
                });

                return myView;
            }



        //********************************************
        //********************************************
        //********************************************
        }

    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE))
                .getActiveNetworkInfo() != null;
    }
    //----------------------------------------------------------

    }