package com.kartikworld.bangali;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class MainMovieActivity extends AppCompatActivity {

    private List<MovieModel> movieList = new ArrayList<>();
    private MoviesAdapter mAdapter;
    DatePicker datePicker;
    Calendar calendar;
    TextView tvDate, tvDate2;
    TextClock textClock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_movie_activity);

        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Time List");
        toolbar.setTitleTextColor(Color.WHITE);
        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------

        //**********************DATE ENG START*********************************************
        tvDate = findViewById(R.id.tvDate);
        tvDate2 = findViewById(R.id.tvDate2);
        datePicker = findViewById(R.id.datePicker);
        tvDate.setText(currentDate());
        textClock = findViewById(R.id.textClock);

        //Setted format for both 12 hours and 24 hour format
        textClock.setFormat12Hour("hh:mm:ss a");
        textClock.setFormat24Hour(null);

        calendar=Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(android.icu.text.DateFormat.FULL & DateFormat.AM_PM_FIELD).format(calendar.getTime());
        tvDate2.setText(currentDate);
        //**********************DATE ENG END*********************************************

        //**********************DATE BNG START*********************************************
        Locale bng = Locale.forLanguageTag("bn-BD");
        DateTimeFormatter timeFormatter = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            timeFormatter = DateTimeFormatter
                    .ofLocalizedTime(FormatStyle.FULL)
                    .withLocale(bng);
        }
        String formattedTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            formattedTime = ZonedDateTime.now(ZoneId.of("Asia/Dhaka")).format(timeFormatter);
        }
        System.out.println(formattedTime);

        //**********************DATE BNG END*********************************************


        //-------RecyclerView------HORIZONTAL-----ItemAnimator------MoviesAdapter--------
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        mAdapter = new MoviesAdapter(movieList);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        prepareMovieData();

    }

    //**********************DATE ENG START2*********************************************
  public String currentDate(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Current Date : ");
        StringBuilder day = stringBuilder.append(datePicker.getDayOfMonth() +"/");
        StringBuilder month = stringBuilder.append((datePicker.getMonth()+1) +"/");
        StringBuilder year = stringBuilder.append(datePicker.getYear());

        return stringBuilder.toString();
    }
    //**********************DATE ENG END2***********************************************

    //**********************DATE BNG START2*********************************************
    public static String convertEnToBn(String number) {
        return number.replaceAll("0", "০")
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

    private void prepareMovieData() {
        MovieModel
        movie = new MovieModel("জানুয়ারি ০১, ২০২৩, রবিবার", "বাংলা তারিখ - পৌষ ১৬, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০১, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৪, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ৯\n" +
                "ভারতীয় সিভিল - পৌষ ১১, ১৯৪৪", "চন্দ্র রাশি - মেষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "বেঙ্গলি উৎসব, ব্রত, উপবাস\n" +
                "নববর্ষের দিন\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ১২:৫২ PM চন্দ্রাস্ট - ০২:০৭ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দশমী upto ০৭:১২ PM followed by একাদশী\n" +
                "নক্ষত্র\n" +
                "অশ্বিনী upto ১২:৪৮ PM followed by ভরণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৭:০৩ AM – ৯:১০ AM, ১২:০১ PM – ০২:৫১ PM\n" +
                "রাতি - ০৭:৩৯ PM – ৯:২৬ PM, ১২:০৬ AM – পরদিন ০১:৫৩ AM, ০২:৪৭ AM – পরদিন ০৬:২১ AM\n" +
                "মহেন্দ্রযোগ\n" +
                "দিন - ০৩:৩৩ PM – ০৪:১৬ PM\n" +
                "বারবেলা - ১০:২০ AM – ১১:৩৯ AM\n" +
                "কালবেলা - ১১:৩৯ AM – ১২:৫৯ PM\n" +
                "কালরাতি - ০১:২০ AM – পরদিন ০৩:০০ AM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ১২:৫২ PM চন্দ্রাস্ট - ০২:০৭ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দশমী upto ১০:৩৮ PM followed by একাদশী\n" +
                "নক্ষত্র\n" +
                "অশ্বিনী upto ০৪:৪৯ PM followed by ভরণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৭:০৩ AM – ৯:১০ AM, ১২:০১ PM – ০২:৫১ PM\n" +
                "রাতি - ০৭:৩৯ PM – ৯:২৬ PM, ১২:০৬ AM – পরদিন ০১:৫৩ AM, ০২:৪৬ AM – পরদিন ০৬:২০ AM\n" +
                "মহেন্দ্রযোগ\n" +
                "দিন - ০৩:৩৪ PM – ০৪:১৬ PM\n" +
                "বারবেলা - ১০:২০ AM – ১১:৩৯ AM\n" +
                "কালবেলা - ১১:৩৯ AM – ১২:৫৯ PM\n" +
                "কালরাতি - ০১:২০ AM – পরদিন ০৩:০০ AM");

        movieList.add(movie);

        //*****************************************

        movie = new MovieModel("জানুয়ারি ০২, ২০২৩, সোমবার", "বাংলা তারিখ - পৌষ ১৭, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০২, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৫, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ১০\n" +
                "ভারতীয় সিভিল - পৌষ ১২, ১৯৪৪", "চন্দ্র রাশি - মেষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "বেঙ্গলি উৎসব, ব্রত, উপবাস\n" +
                "পুত্রদা একাদশী\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ০১:২৮ PM চন্দ্রাস্ট - ০৩:০২ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ একাদশী upto ০৮:২৩ PM followed by দ্বাদশী\n" +
                "নক্ষত্র\n" +
                "ভরণী upto ০২:২৩ PM followed by কৃত্তিকা\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:৪৬ AM, ৯:১১ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৩৯ PM – ১১:১৩ PM, ০২:৪৭ AM – পরদিন ০৩:৪০ AM\n" +
                "কালবেলা - ০৭:৪০ AM – ৯:০০ AM\n" +
                "বারবেলা - ০২:১৯ PM – ০৩:৩৯ PM\n" +
                "কালরাতি - ১০:০০ PM – ১১:৪০ PM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ০১:২৮ PM চন্দ্রাস্ট - ০৩:০২ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ একাদশী upto ১০:৪০ PM followed by দ্বাদশী\n" +
                "নক্ষত্র\n" +
                "ভরণী upto ০৫:১৯ PM followed by কৃত্তিকা\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২০ AM – ০৭:৪৫ AM, ৯:১১ AM – ১১:১৮ AM\n" +
                "রাতি - ০৭:৪০ PM – ১১:১৩ PM, ০২:৪৭ AM – পরদিন ০৩:৪০ AM\n" +
                "কালবেলা - ০৭:৪০ AM – ৯:০০ AM\n" +
                "বারবেলা - ০২:২০ PM – ০৩:৪০ PM\n" +
                "কালরাতি - ১০:০০ PM – ১১:৪০ PM");

        movieList.add(movie);

        //*****************************************

        movie = new MovieModel("জানুয়ারি ০৩, ২০২৩, মঙ্গলবার\n", "বাংলা তারিখ - পৌষ ১৮, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০৩, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৬, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ১১\n" +
                "ভারতীয় সিভিল - পৌষ ১৩, ১৯৪৪", "চন্দ্র রাশি - বৃষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৫:০০ PM\n" +
                "চন্দ্রোদয় - ০২:০৮ PM চন্দ্রাস্ট - ০৩:৫৮ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দ্বাদশী upto ১০:০২ PM followed by ত্রয়োদশী\n" +
                "নক্ষত্র\n" +
                "কৃত্তিকা upto ০৪:২৬ PM followed by রোহিণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:০৩ AM, ০৭:৪৬ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৪০ PM – ০৮:৩৩ PM, ৯:২৭ PM – পরদিন ১২:০৭ AM, ০১:৫৪ AM – পরদিন ০৩:৪১ AM, ০৫:২৮ AM – পরদিন ০৬:২১ AM\n" +
                "বারবেলা - ০৭:৪১ AM – ৯:০১ AM\n" +
                "কালবেলা - ০১:০০ PM – ০২:২০ PM\n" +
                "কালরাতি - ০৬:৪০ PM – ০৮:২০ PM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৫:০০ PM\n" +
                "চন্দ্রোদয় - ০২:০৮ PM চন্দ্রাস্ট - ০৩:৫৮ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দ্বাদশী upto ১১:১৪ PM followed by ত্রয়োদশী\n" +
                "নক্ষত্র\n" +
                "কৃত্তিকা upto ০৬:১৮ PM followed by রোহিণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:০৩ AM, ০৭:৪৬ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৪০ PM – ০৮:৩৪ PM, ৯:২৭ PM – পরদিন ১২:০৭ AM, ০১:৫৪ AM – পরদিন ০৩:৪১ AM, ০৫:২৭ AM – পরদিন ০৬:২১ AM\n" +
                "বারবেলা - ০৭:৪১ AM – ৯:০০ AM\n" +
                "কালবেলা - ০১:০০ PM – ০২:২০ PM\n" +
                "কালরাতি - ০৬:৪০ PM – ০৮:২০ PM");
        movieList.add(movie);

        //*****************************************

        movie = new MovieModel("জানুয়ারি ০৪, ২০২৩, রবিবার", "বাংলা তারিখ - পৌষ ১৬, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০১, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৪, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ৯\n" +
                "ভারতীয় সিভিল - পৌষ ১১, ১৯৪৪", "চন্দ্র রাশি - মেষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "বেঙ্গলি উৎসব, ব্রত, উপবাস\n" +
                "নববর্ষের দিন\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ১২:৫২ PM চন্দ্রাস্ট - ০২:০৭ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দশমী upto ০৭:১২ PM followed by একাদশী\n" +
                "নক্ষত্র\n" +
                "অশ্বিনী upto ১২:৪৮ PM followed by ভরণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৭:০৩ AM – ৯:১০ AM, ১২:০১ PM – ০২:৫১ PM\n" +
                "রাতি - ০৭:৩৯ PM – ৯:২৬ PM, ১২:০৬ AM – পরদিন ০১:৫৩ AM, ০২:৪৭ AM – পরদিন ০৬:২১ AM\n" +
                "মহেন্দ্রযোগ\n" +
                "দিন - ০৩:৩৩ PM – ০৪:১৬ PM\n" +
                "বারবেলা - ১০:২০ AM – ১১:৩৯ AM\n" +
                "কালবেলা - ১১:৩৯ AM – ১২:৫৯ PM\n" +
                "কালরাতি - ০১:২০ AM – পরদিন ০৩:০০ AM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ১২:৫২ PM চন্দ্রাস্ট - ০২:০৭ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দশমী upto ১০:৩৮ PM followed by একাদশী\n" +
                "নক্ষত্র\n" +
                "অশ্বিনী upto ০৪:৪৯ PM followed by ভরণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৭:০৩ AM – ৯:১০ AM, ১২:০১ PM – ০২:৫১ PM\n" +
                "রাতি - ০৭:৩৯ PM – ৯:২৬ PM, ১২:০৬ AM – পরদিন ০১:৫৩ AM, ০২:৪৬ AM – পরদিন ০৬:২০ AM\n" +
                "মহেন্দ্রযোগ\n" +
                "দিন - ০৩:৩৪ PM – ০৪:১৬ PM\n" +
                "বারবেলা - ১০:২০ AM – ১১:৩৯ AM\n" +
                "কালবেলা - ১১:৩৯ AM – ১২:৫৯ PM\n" +
                "কালরাতি - ০১:২০ AM – পরদিন ০৩:০০ AM");


        movieList.add(movie);

        //*****************************************

        movie = new MovieModel("জানুয়ারি ০৫, ২০২৩, সোমবার", "বাংলা তারিখ - পৌষ ১৭, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০২, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৫, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ১০\n" +
                "ভারতীয় সিভিল - পৌষ ১২, ১৯৪৪", "চন্দ্র রাশি - মেষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "বেঙ্গলি উৎসব, ব্রত, উপবাস\n" +
                "পুত্রদা একাদশী\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ০১:২৮ PM চন্দ্রাস্ট - ০৩:০২ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ একাদশী upto ০৮:২৩ PM followed by দ্বাদশী\n" +
                "নক্ষত্র\n" +
                "ভরণী upto ০২:২৩ PM followed by কৃত্তিকা\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:৪৬ AM, ৯:১১ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৩৯ PM – ১১:১৩ PM, ০২:৪৭ AM – পরদিন ০৩:৪০ AM\n" +
                "কালবেলা - ০৭:৪০ AM – ৯:০০ AM\n" +
                "বারবেলা - ০২:১৯ PM – ০৩:৩৯ PM\n" +
                "কালরাতি - ১০:০০ PM – ১১:৪০ PM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২০ AM সূর্যাস্ত - ০৪:৫৯ PM\n" +
                "চন্দ্রোদয় - ০১:২৮ PM চন্দ্রাস্ট - ০৩:০২ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ একাদশী upto ১০:৪০ PM followed by দ্বাদশী\n" +
                "নক্ষত্র\n" +
                "ভরণী upto ০৫:১৯ PM followed by কৃত্তিকা\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২০ AM – ০৭:৪৫ AM, ৯:১১ AM – ১১:১৮ AM\n" +
                "রাতি - ০৭:৪০ PM – ১১:১৩ PM, ০২:৪৭ AM – পরদিন ০৩:৪০ AM\n" +
                "কালবেলা - ০৭:৪০ AM – ৯:০০ AM\n" +
                "বারবেলা - ০২:২০ PM – ০৩:৪০ PM\n" +
                "কালরাতি - ১০:০০ PM – ১১:৪০ PM");

        movieList.add(movie);

        //***********************************

        movie = new MovieModel("জানুয়ারি ০৬, ২০২৩, মঙ্গলবার\n", "বাংলা তারিখ - পৌষ ১৮, ১৪২৯\n" +
                "গ্রেগরীয় - জানুয়ারি ০৩, ২০২৩\n" +
                "বিক্রম সম্বৎ - পৌষ ২৬, ২০৭৯\n" +
                "শকা সংবৎ - পৌষ ১১\n" +
                "ভারতীয় সিভিল - পৌষ ১৩, ১৯৪৪", "চন্দ্র রাশি - বৃষ\n" +
                "সূর্য রাশি - ধনু\n" +
                "দৃকসিদ্ধ\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৫:০০ PM\n" +
                "চন্দ্রোদয় - ০২:০৮ PM চন্দ্রাস্ট - ০৩:৫৮ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দ্বাদশী upto ১০:০২ PM followed by ত্রয়োদশী\n" +
                "নক্ষত্র\n" +
                "কৃত্তিকা upto ০৪:২৬ PM followed by রোহিণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:০৩ AM, ০৭:৪৬ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৪০ PM – ০৮:৩৩ PM, ৯:২৭ PM – পরদিন ১২:০৭ AM, ০১:৫৪ AM – পরদিন ০৩:৪১ AM, ০৫:২৮ AM – পরদিন ০৬:২১ AM\n" +
                "বারবেলা - ০৭:৪১ AM – ৯:০১ AM\n" +
                "কালবেলা - ০১:০০ PM – ০২:২০ PM\n" +
                "কালরাতি - ০৬:৪০ PM – ০৮:২০ PM\n" +
                "সূর্য সিদ্ধান্ত\n" +
                "সূর্যোদয় - ০৬:২১ AM সূর্যাস্ত - ০৫:০০ PM\n" +
                "চন্দ্রোদয় - ০২:০৮ PM চন্দ্রাস্ট - ০৩:৫৮ AM\n" +
                "তিথি\n" +
                "শুক্ল পক্ষ দ্বাদশী upto ১১:১৪ PM followed by ত্রয়োদশী\n" +
                "নক্ষত্র\n" +
                "কৃত্তিকা upto ০৬:১৮ PM followed by রোহিণী\n" +
                "অমৃতযোগ\n" +
                "দিন - ০৬:২১ AM – ০৭:০৩ AM, ০৭:৪৬ AM – ১১:১৯ AM\n" +
                "রাতি - ০৭:৪০ PM – ০৮:৩৪ PM, ৯:২৭ PM – পরদিন ১২:০৭ AM, ০১:৫৪ AM – পরদিন ০৩:৪১ AM, ০৫:২৭ AM – পরদিন ০৬:২১ AM\n" +
                "বারবেলা - ০৭:৪১ AM – ৯:০০ AM\n" +
                "কালবেলা - ০১:০০ PM – ০২:২০ PM\n" +
                "কালরাতি - ০৬:৪০ PM – ০৮:২০ PM");
        movieList.add(movie);
        //*********************************


        movie = new MovieModel("My Name Kartik Roy", "Banglagor, Ranisankail, Thakurgaon", "Birthday : 27 January 2001");
        movieList.add(movie);

        mAdapter.notifyDataSetChanged();
    }
}