package com.kartikworld.bangali;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;



public class EmbedPlayer extends AppCompatActivity {

    WebView webView;
    public static String video_url="";
    LinearLayout _rootLay;
    LayoutInflater layoutInflater;
    ImageView myImage;


    ArrayList<HashMap <String , String >> arrayList=new ArrayList<>();
    HashMap <String , String > hashMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.embed_player);


        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(video_url);
        _rootLay=findViewById(R.id._rootLay);

        layoutInflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View myView= layoutInflater.inflate(R.layout.navigation,_rootLay);
        myImage=myView.findViewById(R.id.myImage);








    }

}