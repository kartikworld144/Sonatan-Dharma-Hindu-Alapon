package com.kartikworld.bangali;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.airbnb.lottie.LottieAnimationView;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

public class PDFViewer extends AppCompatActivity {

    PDFView pdfView;
    LottieAnimationView myLottie;
    public  static  String PDF_ASSET_NAME = "";
    public  static  String PDF_TITLE = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_page);
        pdfView = findViewById(R.id.pdfView);
        myLottie = findViewById(R.id.myLottie);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(""+PDF_TITLE);


        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        pdfView.setVisibility(View.INVISIBLE);
        myLottie.setVisibility(View.VISIBLE);


        pdfView.fromAsset(PDF_ASSET_NAME)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                pdfView.setVisibility(View.VISIBLE);
                                myLottie.setVisibility(View.GONE);
                            }
                        }, 1500);

                    }
                })
                .load();




    }
}