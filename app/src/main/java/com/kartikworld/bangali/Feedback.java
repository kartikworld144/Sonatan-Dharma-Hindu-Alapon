package com.kartikworld.bangali;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Feedback extends AppCompatActivity {

    EditText edName,edFeedback;
    Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);

        edName=findViewById(R.id.edName);
        edFeedback=findViewById(R.id.edFeedback);
        buttonSend=findViewById(R.id.buttonSend);



        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.setType("message/rfc822/html");
                intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"sonatondhormohindualapon@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback From সনাতন ধর্ম হিন্দু আলাপন App");
                intent.putExtra(Intent.EXTRA_TEXT, "Name : " + edName.getText().toString() + "\n\nMessage : " + edFeedback.getText().toString());
                try {
                    startActivity(Intent.createChooser(intent, "Please select Email"));
                }
                catch (android.content.ActivityNotFoundException ex)
                {
                    Toast.makeText(Feedback.this, "There are no Email Clients", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }
}