package com.kartikworld.bangali;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;


public class Register extends AppCompatActivity implements View.OnClickListener{

    EditText et_signup_email,et_signup_password;
    Button btn_signup;
    TextView swipe_signup_Left;
    private FirebaseAuth mAuth;
    ProgressBar progressBar;
    TextView marquee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);




        progressBar= findViewById(R.id.progressBar);
        swipe_signup_Left= findViewById(R.id.swipe_signup_Left);

        mAuth = FirebaseAuth.getInstance();
        et_signup_email= findViewById(R.id.et_signup_email);
        et_signup_password= findViewById(R.id.et_signup_password);

        btn_signup=findViewById(R.id.btn_signup);




        btn_signup.setOnClickListener(this);
        swipe_signup_Left.setOnClickListener(this);



        //*************************************************
        marquee = findViewById(R.id.marquee);
        marquee.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        marquee.setText("সনাতন ধর্ম হিন্দু আলাপনে আপনাকে স্বাগতম। যারা তীর্থযাত্রা করতে পারেন না তাদের জন্য অ্যাপটি বিশেষভাবে কার্যকর। সনাতন ধর্ম হিন্দু আলাপন অ্যাপটি হিন্দু ধর্মের তথ্যের বৃহত্তর আধারে দ্রুত অ্যাক্সেস দেয়। অ্যাপটি ভক্তদের ধর্ম-কর্ম, পূজা, ব্রতকথা, শ্রাদ্ধ (ভোজ), বৈষ্ণব ও গৌ সেবা সম্পর্কে জানতে সাহায্য করবে।");
        marquee.setSelected(true);
        marquee.setSingleLine(true);
        marquee.append("");
        marquee.setVisibility(View.VISIBLE);
        //*************************************************
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_signup:
                break;
            case R.id.swipe_signup_Left:
                startActivity(new Intent(Register.this, Login.class));
                break;
        }


        userRegister();
    }

    private void userRegister() {

        String email=et_signup_email.getText().toString().trim();
        String signup_password=et_signup_password.getText().toString().trim();


        //checking validity

        if (email.isEmpty())
        {
            et_signup_email.setError("Enter in email address");
            et_signup_email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            et_signup_email.setError("Enter a valid email address");
            et_signup_email.requestFocus();
            return;
        }
        if (signup_password.isEmpty())
        {
            et_signup_password.setError("Enter a password");
            et_signup_password.requestFocus();
            return;
        }
        if (signup_password.length()<6)
        {
            et_signup_password.setError("Minimum length of a password should be 6");
            et_signup_password.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, signup_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            finish();
                            Intent intent = new Intent(getApplicationContext(),Navigation.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);

                            Toast.makeText(getApplicationContext(),"Register is successful",Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            if (task.getException()instanceof FirebaseAuthUserCollisionException)
                            {
                                Toast.makeText(getApplicationContext(),"This email is already registered",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(getApplicationContext(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });



    }
}