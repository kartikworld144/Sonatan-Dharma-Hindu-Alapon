package com.kartikworld.bangali;

import static com.kartikworld.bangali.MakeVideoList.createCategoryForWebsite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;


public class LoginActivity extends AppCompatActivity{

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab_fb, fab_google, fab_twitter;
    ImageView image_logo;
    TextView marquee;
    float v=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        fab_fb = findViewById(R.id.fab_fb);
        fab_google = findViewById(R.id.fab_google);
        fab_twitter = findViewById(R.id.fab_twitter);
        image_logo = findViewById(R.id.image_logo);
        marquee = findViewById(R.id.marquee);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addTab(tabLayout.newTab());
        tabLayout.addTab(tabLayout.newTab());

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        fab_fb.setTranslationY(300);
        fab_google.setTranslationY(300);
        fab_twitter.setTranslationY(300);
        tabLayout.setTranslationX(300);
        image_logo.setTranslationX(300);
        marquee.setTranslationX(300);


        fab_fb.setAlpha(v);
        fab_google.setAlpha(v);
        fab_twitter.setAlpha(v);
        tabLayout.setAlpha(v);
        image_logo.setAlpha(v);
        marquee.setAlpha(v);
        fab_google.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
        fab_fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        fab_twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(1000).start();
        tabLayout.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        image_logo.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();
        marquee.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();



        //*************************************************
        marquee = findViewById(R.id.marquee);
        marquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marquee.setText("সনাতন ধর্ম হিন্দু আলাপনে আপনাকে স্বাগতম। যারা তীর্থযাত্রা করতে পারেন না তাদের জন্য অ্যাপটি বিশেষভাবে কার্যকর। সনাতন ধর্ম হিন্দু আলাপন অ্যাপটি হিন্দু ধর্মের তথ্যের বৃহত্তর আধারে দ্রুত অ্যাক্সেস দেয়। অ্যাপটি ভক্তদের ধর্ম-কর্ম, পূজা, ব্রতকথা, শ্রাদ্ধ (ভোজ), বৈষ্ণব ও গৌ সেবা সম্পর্কে জানতে সাহায্য করবে।");
        marquee.setSelected(true);
        marquee.setSingleLine(true);
        marquee.append("");
        marquee.setVisibility(View.VISIBLE);
        //*************************************************

        fab_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebBrowser.WEBSITE_LINK="https://www.kartikworld.com";
                Intent intent = new Intent(LoginActivity.this,WebBrowser.class);
                startActivity(intent);
            }
        });

        fab_fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebBrowser.WEBSITE_LINK="https://www.facebook.com/profile.php?id=100015893013128";
                Intent intent = new Intent(LoginActivity.this,WebBrowser.class);
                startActivity(intent);
            }
        });

        fab_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                WebBrowser.WEBSITE_LINK="https://www.twitter.com";
                Intent intent = new Intent(LoginActivity.this,WebBrowser.class);
                startActivity(intent);
            }
        });


    }
}