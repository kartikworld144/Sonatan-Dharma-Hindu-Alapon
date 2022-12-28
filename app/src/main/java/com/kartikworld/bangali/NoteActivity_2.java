package com.kartikworld.bangali;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class NoteActivity_2 extends AppCompatActivity {
    public static String TEXT="";

    TextView tvText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_2);
        tvText=findViewById(R.id.tvText);
        tvText.setText(TEXT);

    }
}