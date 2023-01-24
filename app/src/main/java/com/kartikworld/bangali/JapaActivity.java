package com.kartikworld.bangali;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class JapaActivity extends AppCompatActivity {

    public static TextView tvResult;
    Button buttonSave, buttonReset;
    TypeWriter buttonAdd;
    private static int count;
    private static int mala;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jopa_activity);

        tvResult = findViewById(R.id.tvView);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonSave = findViewById(R.id.buttonSave);
        buttonReset = findViewById(R.id.buttonReset);

        count = SharePref.TotalLoadSharePreference(this);
        mala = count / 108;

        tvResult.setText(getString(R.string.mohamontra_japechin)+count+" বার / "+mala+ " মালা");
        getDigitENGFromBNG();
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Back");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                count++;
                mala = count / 108;
                getDigitENGFromBNG();
                //SharePref.TotalSaveSharePreference0(getApplicationContext(),count);
                buttonAdd.setText("");
                buttonAdd.SetCharacterDelay(50);

                if (count<=1 || count==109 || count==217 || count==325 || count==433 || count==541 || count==649 || count==757 || count==865 || count==973 || count==1081 || count==1189 || count==1297 || count==1405 || count==1513 || count==1621 || count==1729) {
                    buttonAdd.AnimateText("জয় শ্রী-কৃষ্ণ-চৈতন্য\nপ্রভু-নিত্যানন্দ।\nশ্রী-অদ্বৈত গদাধর\nশ্রীবাসাধি-গৌর\n-ভক্ত-বৃন্দ।।\nহরে কৃষ্ণ হরে কৃষ্ণ\nকৃষ্ণ কৃষ্ণ হরে হরে\nহরে রাম হরে রাম\nরাম রাম হরে হরে\n");
                }
                else {
                    buttonAdd.AnimateText("হরে কৃষ্ণ হরে কৃষ্ণ\nকৃষ্ণ কৃষ্ণ হরে হরে\n\n"+"হরে রাম হরে রাম\nরাম রাম হরে হরে\n");
                }
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String jop_save =Integer.toString(count);
                SharePref.TotalSaveSharePreference(getApplicationContext(), Integer.parseInt(jop_save));
                Toast.makeText(JapaActivity.this, "সংরক্ষণ হয়েছে", Toast.LENGTH_SHORT).show();
                getDigitENGFromBNG();
            }
        });


      /*buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharePref.TotalRemoveSharePreference(getApplication());
                /*  PendingIntent pendingIntent = PendingIntent.getActivity(JapaActivity.this,1000,getIntent(),PendingIntent.FLAG_CANCEL_CURRENT);
                AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                alarmManager.set(AlarmManager.RTC,System.currentTimeMillis() +2000,pendingIntent);
                System.exit(0);
            }
        });
        */


    }


   public void resetPreference(View view){
       SharePref.TotalRemoveSharePreference(this);
       Toast.makeText(JapaActivity.this, "পুনঃস্থাপন হয়েছে", Toast.LENGTH_SHORT).show();
       tvResult.clearComposingText();
       count = 0;
       String jop =Integer.toString(count);
       jop="০";

       mala = 0;
       String mala_108 =Integer.toString(mala);
       mala_108="০";


       getDigitENGFromBNG();
       tvResult.setText(getString(R.string.mohamontra_japechin)+jop+" বার / "+mala_108+ " মালা");


   }
   /*
    private static final char[] BngDigits = {'০','১','২','৩','৪','৫','৬','৭','৮','৯'};
    private static final char[] EngDigits = {'0','1','2','3','4','5','6','7','8','9'};

    public  static final String  getDigitBNGFromENG(String number){
        if(number==null)
            return new String("");
        StringBuilder builder = new StringBuilder();
        try{
            for(int i =0;i<number.length();i++){
                if(Character.isDigit(number.charAt(i))){
                    if(((int)(number.charAt(i))-48)<=9){
                        builder.append(BngDigits[(int)(number.charAt(i))-48]);
                    }else{
                        builder.append(number.charAt(i));
                    }
                }else{
                    builder.append(number.charAt(i));
                }
            }
        }catch(Exception e){
            //logger.debug("getDigitBNGFromENG: ",e);
            return new String("");
        }
        return builder.toString();
    }

     */

    public String  getDigitENGFromBNG(){

        String jop =Integer.toString(count);
        jop = jop.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        jop = jop.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        jop = jop.replace("8", "৮").replace("9", "৯");

        String mala_108 =Integer.toString(mala);
        mala_108 = mala_108.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        mala_108 = mala_108.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        mala_108 = mala_108.replace("8", "৮").replace("9", "৯");

        tvResult.setText(getString(R.string.mohamontra_japechin)+jop+" বার / "+mala_108+ " মালা");

        return (null);
    }



}