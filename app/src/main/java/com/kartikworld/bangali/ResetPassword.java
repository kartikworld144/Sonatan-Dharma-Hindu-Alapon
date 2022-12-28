package com.kartikworld.bangali;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class ResetPassword extends AppCompatActivity {

    EditText edtNumber, et_code, etPassword, etRepeatPassword;
    final int MIN_PASSWORD_LENGTH = 6;
    Button sendRequest, resetPassword;
    public FirebaseAuth firebaseAuth;
    public FirebaseUser firebaseUser;
    public FirebaseFirestore firebaseFirestore;
    StorageReference storageReference;
    String userId;
    ProgressBar progressBar;
    // string for storing our verification ID
    private String verificationId;
    private CountryCodePicker ccp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reset_password);


        edtNumber = findViewById(R.id.edtNumber);
        et_code = findViewById(R.id.et_code);
        etPassword = findViewById(R.id.et_password);
        etRepeatPassword = findViewById(R.id.et_repeat_password);
        sendRequest = findViewById(R.id.sendRequest);
        resetPassword = findViewById(R.id.resetPassword);
        progressBar = findViewById(R.id.progressBar);


        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        userId = firebaseAuth.getCurrentUser().getUid();
        firebaseUser = firebaseAuth.getCurrentUser();
        storageReference = FirebaseStorage.getInstance().getReference();



        //*****************************************
        viewInitializations();
        viewInitializationsOfCountryCod();
        viewCountryCod();

        //*****************************************


        sendRequest.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {



                progressBar.setVisibility(View.VISIBLE);


                if (TextUtils.isEmpty(edtNumber.getText().toString())) {
                    // when mobile number text field is empty
                    // displaying a toast message.
                    progressBar.setVisibility(View.GONE);

                    edtNumber.requestFocus();
                    Toast.makeText(ResetPassword.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    // if the text field is not empty we are calling our
                    // send OTP method for getting OTP from Firebase.
                    progressBar.setVisibility(View.GONE);

                    String phone = "+880||+91" + edtNumber.getText().toString();
                    sendVerificationCode(phone);
                }

            }

        });



        // initializing on click listener
        // for verify otp button
        resetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validateInput()){


                // validating if the OTP text field is empty or not.
                if (TextUtils.isEmpty(et_code.getText().toString())) {
                    // if the OTP text field is empty display
                    // a message to user to enter OTP
                    Toast.makeText(ResetPassword.this, "Please enter OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // if OTP field is not empty calling
                    // method to verify the OTP.
                    verifyCode(et_code.getText().toString());
                }
                }else {
                    Toast.makeText(ResetPassword.this, "Please enter all input", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }

    private void viewInitializationsOfCountryCod() {

        ccp = (CountryCodePicker) findViewById(R.id.ccp);
        edtNumber = (EditText) findViewById(R.id.edtNumber);

    }
    private void viewCountryCod(){
        edtNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = ccp.getSelectedCountryCodeWithPlus();
                String country=ccp.getSelectedCountryEnglishName();
                String number= edtNumber.getText().toString().trim();
                String phoneNumber = code + number;

                //Toast makeText
                Context context=getApplicationContext();
                CharSequence text= "country - "+country+", Value sent : "+code+""+number;
                int duration=Toast.LENGTH_LONG;
                //edtNumber.setText(phoneNumber);
                Toast toast=Toast.makeText(context, text, duration);
                toast.show();

            }
        });
    }
/*
    public void SaveContact(View v) {
        EditText phoneNumberField = (EditText) findViewById(R.id.edtNumber);
        if (phoneNumberField.getText().toString().isEmpty()) {

        } else {
            Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
            edtNumber = (EditText) findViewById(R.id.edtNumber);
            CountryCodePicker cpp = (CountryCodePicker) findViewById(R.id.ccp);
            String mNo = cpp.getFullNumberWithPlus() + edtNumber.getText();
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, mNo);
            startActivity(intent);
        }
    }
*/
    private void signInWithCredential(PhoneAuthCredential credential) {
        // inside this method we are checking if
        // the code entered is correct or not.
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // if the code is correct and the task is successful
                            // we are sending our user to new activity.
                            Intent intent = new Intent(ResetPassword.this, Navigation.class);
                            startActivity(intent);
                            finish();
                        } else {
                            // if the code is not correct then we are
                            // displaying an error message to the user.
                            Toast.makeText(ResetPassword.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }



    private void sendVerificationCode(String number) {
        // this method is used for getting
        // OTP on user phone number.
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(firebaseAuth)
                        .setPhoneNumber(number)            // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(mCallBack)           // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    // callback method is called on Phone auth provider.
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks

            // initializing our callbacks for on
            // verification callback method.
            mCallBack = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        // below method is used when
        // OTP is sent from Firebase
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            // when we receive the OTP it
            // contains a unique id which
            // we are storing in our string
            // which we have already created.
            verificationId = s;
        }

        // this method is called when user
        // receive OTP from Firebase.
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            // below line is used for getting OTP code
            // which is sent in phone auth credentials.
            final String code = phoneAuthCredential.getSmsCode();

            // checking if the code
            // is null or not.
            if (code != null) {
                // if the code is not null then
                // we are setting that code to
                // our OTP edittext field.
                et_code.setText(code);

                // after setting this code
                // to OTP edittext field we
                // are calling our verifycode method.
                verifyCode(code);
            }
        }

        // this method is called when firebase doesn't
        // sends our OTP code due to any error or issue.
        @Override
        public void onVerificationFailed(FirebaseException e) {
            // displaying error message with firebase exception.
            Toast.makeText(ResetPassword.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    };

    // below method is use to verify code from Firebase.
    private void verifyCode(String code) {
        // below line is used for getting
        // credentials from our verification id and code.
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);

        // after getting credential we are
        // calling sign in method.
        signInWithCredential(credential);
    }





            void viewInitializations() {




                //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
                // To show back button in actionbar
                Toolbar toolbar = findViewById(R.id.toolbar);
                setSupportActionBar(toolbar);
                getSupportActionBar().setTitle("Reset Password");

                // add back arrow to toolbar
                if (getSupportActionBar() != null){
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setDisplayShowHomeEnabled(true);
                }
                // don't forget click listener for back button
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
                //-------------------------------------------------------------------------------------------------

            }




            // Checking if the input in form is valid
            boolean validateInput() {

                if (edtNumber.getText().toString().equals("")) {
                    edtNumber.setError("Please Enter Valid Number");
                    return false;
                }

                if (et_code.getText().toString().equals("")) {
                    et_code.setError("Please Enter Valid Code");
                    return false;
                }


                if (etPassword.getText().toString().equals("")) {
                    etPassword.setError("Please Enter Password");
                    return false;
                }
                if (etRepeatPassword.getText().toString().equals("")) {
                    etRepeatPassword.setError("Please Enter Repeat Password");
                    return false;
                }

                // checking minimum password Length
                if (etPassword.getText().length() < MIN_PASSWORD_LENGTH) {
                    etPassword.setError("Password Length must be more than " + MIN_PASSWORD_LENGTH + "characters");
                    return false;
                }

                // Checking if repeat password is same
                if (!etPassword.getText().toString().equals(etRepeatPassword.getText().toString())) {
                    etRepeatPassword.setError("Password does not match");
                    return false;
                }
                return true;
            }




        }