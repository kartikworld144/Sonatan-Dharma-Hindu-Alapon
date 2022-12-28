package com.kartikworld.bangali;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginTabFragment extends Fragment {

    public float v = 0;
    SharedPreferences.Editor editor;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_tab_fragment, container, false);

        TextInputLayout loginLayoutEmailInput = root.findViewById(R.id.loginLayoutEmailInput);
        TextInputLayout loginLayoutPasswordInput = root.findViewById(R.id.loginLayoutPasswordInput);
        EditText et_login_email = root.findViewById(R.id.et_login_email);
        EditText et_login_password = root.findViewById(R.id.et_login_password);
        TextView textView_forgot_password = root.findViewById(R.id.textView_forgot_password);
        Button button_login = root.findViewById(R.id.button_login);
        ProgressBar progressBar = root.findViewById(R.id.progressBar);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        //FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        //******************************************************************************************

        loginLayoutEmailInput.setTranslationX(300);
        loginLayoutPasswordInput.setTranslationX(300);
        textView_forgot_password.setTranslationX(300);
        button_login.setTranslationX(300);

        loginLayoutEmailInput.setAlpha(v);
        loginLayoutPasswordInput.setAlpha(v);
        textView_forgot_password.setAlpha(v);
        button_login.setAlpha(v);

        loginLayoutEmailInput.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        loginLayoutPasswordInput.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        textView_forgot_password.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        button_login.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();

        //******************************************************************************************
        textView_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ResetPassword.class);
                startActivity(intent);
            }
        });
        //******************************************************************************************************

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.contains("saved_Email"))
        {
            startActivity(new Intent(getActivity(), Navigation.class));
            getActivity().finish();
        }

        else {

            button_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String email = et_login_email.getText().toString().trim();
                    String password = et_login_password.getText().toString().trim();


                    //checking validity
                    //******************************************************************************************

                    if (email.isEmpty()) {
                        et_login_email.setError("Enter in email address");
                        et_login_email.requestFocus();
                        return;
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        et_login_email.setError("Enter a valid email address");
                        et_login_email.requestFocus();
                        return;
                    }
                    if (password.isEmpty()) {
                        et_login_password.setError("Enter a password");
                        et_login_password.requestFocus();
                        return;
                    }
                    if (password.length() < 6) {
                        et_login_password.setError("Minimum length of a password should be 6");
                        et_login_password.requestFocus();
                        return;
                    }
                    //******************************************************************************************

                    progressBar.setVisibility(View.VISIBLE);

                    //******************************************************************************************
                    firebaseAuth.signInWithEmailAndPassword(email, password)

                           /* .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progressBar.setVisibility(View.GONE);

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        editor.putString("saved_Email", email);
                                        editor.putString("saved_Password", password);
                                        editor.commit();
                                        Intent intent = new Intent(getActivity(), Navigation.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                        Toast.makeText(getActivity(), "login is successful", Toast.LENGTH_SHORT).show();
                                        getActivity().finish();

                                    } else {

                                        // If sign in fails, display a message to the user.
                                        Toast.makeText(getActivity(), "Please register first", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    //******************************************************************************************
                            */
                            .addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                                @Override
                                public void onSuccess(AuthResult authResult) {

                                    // Sign in success, update UI with the signed-in user's information
                                    editor.putString("saved_Email", email);
                                    editor.putString("saved_Password", password);
                                    editor.commit();
                                    Intent intent = new Intent(getActivity(), Navigation.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    Toast.makeText(getActivity(), "login is successful", Toast.LENGTH_SHORT).show();
                                    getActivity().finish();
                                }
                            })

                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {

                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getActivity(), "Please register first", Toast.LENGTH_SHORT).show();
                                }
                            });
                    //******************************************************************************************

                }


            });
        }


            return root;



        }
    }

