package com.kartikworld.bangali;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyTube extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;
    ListView listView;
    LinearLayout layNoNet;
    ProgressBar progressBar;
    static ArrayList <HashMap <String , String >> arrayList=new ArrayList<>();
    HashMap <String , String > hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mytube);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        listView = findViewById(R.id.listView);
        layNoNet = findViewById(R.id.layNoNet);
        progressBar = findViewById(R.id.progressBar);

        //+++++++++++++++++++++++++++++++++++++++++++++++
        if(!isNetworkAvailable(MyTube.this)){
            listView.setVisibility(View.GONE);
            layNoNet.setVisibility(View.VISIBLE);
        }else{
            listView.setVisibility(View.VISIBLE);
            layNoNet.setVisibility(View.GONE);
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

        Button tryAgain = (Button) findViewById(R.id.tryAgain);

        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MyTube.this, MyTube.class);
                startActivity(intent);
                finish();
            }
        });


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MyTube Bangla");
        toolbar.setTitleTextColor(Color.RED);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        String url = "https://kartikworlddotcom.000webhostapp.com/apps/videos.json";

        JsonArrayRequest arrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                progressBar.setVisibility(View.GONE);

                try {
                    for (int x=0;x<response.length();x++) {
                        JSONObject jsonObject=response.getJSONObject(x);
                        String title =jsonObject.getString("title");
                        String link =jsonObject.getString("video_id");
                        hashMap=new HashMap<>();
                        hashMap.put("title",title);
                        hashMap.put("link",link);
                        arrayList.add(hashMap);
                    }
                    MyAdapter myAdapter=new MyAdapter();
                    listView.setAdapter(myAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                progressBar.setVisibility(View.GONE);
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(MyTube.this);
        requestQueue.add(arrayRequest);
    }
    //============================================================


    class MyAdapter extends BaseAdapter{

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
            View myView = layoutInflater.inflate(R.layout.json_item,null);

            //********************************************
            Animation animation = AnimationUtils.loadAnimation(MyTube.this, R.anim.splash_top);
            animation.setStartOffset(i*10);
            myView.startAnimation(animation);
            //********************************************

            TextView youtubeTitle = myView.findViewById(R.id.youtubeTitle);
            ImageView youtubeThumb = myView.findViewById(R.id.youtubeThumb);

            HashMap<String,String>hashMap = arrayList.get(i);
            String title = hashMap.get("title");
            String link = hashMap.get("link");
            String image = "https://i.ytimg.com/vi/"+link+"/0.jpg";

            youtubeTitle.setText((1+i++ )+"."+title);
            Picasso.get()
                    .load(image)
                    .placeholder(R.drawable.bangladesh)
                    .into(youtubeThumb);

            youtubeThumb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    EmbedPlayer.video_url = "https://www.youtube.com/embed/"+link;

                    Intent intent = new Intent(MyTube.this, EmbedPlayer.class);
                    startActivity(intent);
                }
            });
            return myView;
            //****************************************************************
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