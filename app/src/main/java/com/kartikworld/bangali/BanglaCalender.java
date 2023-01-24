package com.kartikworld.bangali;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

public class BanglaCalender extends AppCompatActivity {


    TextView enDate,bnDate,date;

    int count;
    Calendar calendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bangla_calender_activity);

        TextClock textClock = findViewById(R.id.textClock);
        enDate = findViewById(R.id.enDate);
        bnDate = findViewById(R.id.bnDate);
        date = findViewById(R.id.date);

        getDigitENGFromBNG();

        //Set format for 12 hour format
        textClock.setFormat12Hour("hh:mm:ss a");
        textClock.setFormat24Hour(null);


        calendar= Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(android.icu.text.DateFormat.FULL & DateFormat.AM_PM_FIELD).format(calendar.getTime());
        enDate.setText(currentDate);




    }
    public String  getDigitENGFromBNG(){

        String jop =Integer.toString(count);
        jop = jop.replace("0", "০").replace("1", "১").replace("2", "২").replace("3", "৩");
        jop = jop.replace("4", "৪").replace("5", "৫").replace("6", "৬").replace("7", "৭");
        jop = jop.replace("8", "৮").replace("9", "৯");
        enDate.setText(jop);

        return (null);
    }

    //**********************DATE BNG START*********************************************
    public static String convertEnToBn(String number) {
        return number
                .replaceAll("0", "০")
                .replaceAll("1", "১")
                .replaceAll("2", "২")
                .replaceAll("3", "৩")
                .replaceAll("4", "৪")
                .replaceAll("5", "৫")
                .replaceAll("6", "৬")
                .replaceAll("7", "৭")
                .replaceAll("8", "৮")
                .replaceAll("9", "৯");
    }
    //**********************DATE BNG END2***********************************************

}