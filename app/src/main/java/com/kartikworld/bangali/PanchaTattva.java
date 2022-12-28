package com.kartikworld.bangali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PanchaTattva extends AppCompatActivity {

    ImageButton imageButton,imageButton1,imageButton2,imageButton3,imageButton4,imageButton5;
    LinearLayout newLinearlayout, linearLayout1,linearLayout2,linearLayout3,linearLayout4,linearLayout5;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pancha_tattva);

        imageButton = findViewById(R.id.imageButton);
        imageButton1 = findViewById(R.id.imageButton1);
        imageButton2 = findViewById(R.id.imageButton2);
        imageButton3 = findViewById(R.id.imageButton3);
        imageButton4 = findViewById(R.id.imageButton4);
        imageButton5 = findViewById(R.id.imageButton5);
        linearLayout1=findViewById(R.id.linearLayout1);
        linearLayout2=findViewById(R.id.linearLayout2);
        linearLayout3=findViewById(R.id.linearLayout3);
        linearLayout4=findViewById(R.id.linearLayout4);
        linearLayout5=findViewById(R.id.linearLayout5);
        textView = findViewById(R.id.textView);
        newLinearlayout=findViewById(R.id.newLinearlayout);


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanchaTattva.this, Settings.class));
            }
        });

        imageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.VISIBLE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                imageButton.setVisibility(View.VISIBLE);
                newLinearlayout.setVisibility(View.GONE);
                textView.setText("শ্রী কৃষ্ণ চৈতন্য মহাপ্রভু");
            }
        });

        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.VISIBLE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                newLinearlayout.setVisibility(View.GONE);
                imageButton.setVisibility(View.VISIBLE);
                textView.setText("শ্রী নিত্যানন্দ প্রভু");
            }
        });

        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.VISIBLE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.GONE);
                newLinearlayout.setVisibility(View.GONE);
                imageButton.setVisibility(View.VISIBLE);
                textView.setText("শ্রী অদ্বৈত আচার্য");
            }
        });

        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.VISIBLE);
                linearLayout5.setVisibility(View.GONE);
                newLinearlayout.setVisibility(View.GONE);
                imageButton.setVisibility(View.VISIBLE);
                textView.setText("শ্রী গদাধর পণ্ডিত");
            }
        });

        imageButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayout1.setVisibility(View.GONE);
                linearLayout2.setVisibility(View.GONE);
                linearLayout3.setVisibility(View.GONE);
                linearLayout4.setVisibility(View.GONE);
                linearLayout5.setVisibility(View.VISIBLE);
                newLinearlayout.setVisibility(View.GONE);
                imageButton.setVisibility(View.VISIBLE);
                textView.setText("শ্রী শ্রীবাস পন্ডিত ঠাকুর");
            }
        });

        //-------------------------------------------------------------------------------------------------

    }
}
