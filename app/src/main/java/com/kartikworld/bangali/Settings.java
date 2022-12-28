package com.kartikworld.bangali;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;

import de.hdodenhof.circleimageview.CircleImageView;

public class Settings extends AppCompatActivity {

    //Initialize variable
    AppCompatButton editButton;
    SwitchCompat switchCompat;
    RelativeLayout signOut,securityPrivacy;
    TextView tvName, tvEmail;
    CircleImageView imgProfile;
    PreferenceManager preferenceManager;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //public static Bitmap PRO_IMG_BITMAP=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES)
        {setTheme(R.style.Dark_Theme);}else{setTheme(R.style.Light_Theme);}
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        //Assign variable
        editButton=findViewById(R.id.editButton);
        switchCompat=findViewById(R.id.switchCompat);
        signOut=findViewById(R.id.signOut);
        securityPrivacy=findViewById(R.id.securityPrivacy);
        tvName=findViewById(R.id.tvName);
        tvEmail=findViewById(R.id.tvEmail);
        imgProfile=findViewById(R.id.imgProfile);

        preferenceManager=PreferenceManager.getInstance(this);
        String previouslyEncodedImage = preferenceManager.getString("image_data");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ){
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imgProfile.setImageBitmap(bitmap);
        }


        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String name = sharedPreferences.getString("saved_Name","Name: Xyz");
        String email = sharedPreferences.getString("saved_Email","Email: xyz@gmail.com");
        tvName.setText(name);
        tvEmail.setText(email);


        //if(PRO_IMG_BITMAP!=null) imgProfile.setImageBitmap(PRO_IMG_BITMAP);


        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES) {
            switchCompat.setChecked(true);
        }else {
            switchCompat.setChecked(false);

        }

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.this, SettingsEdit.class));
            }
        });

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
            }


        });


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Settings");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        securityPrivacy.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(Settings.this, PrivacyPolicy.class));
            }
        });
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        signOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Settings.this, SettingsEdit.class));
            }
        });
        //-------------------------------------------------------------------------------------------------

    }
}