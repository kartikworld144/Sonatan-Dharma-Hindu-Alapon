package com.kartikworld.bangali;

import android.content.Context;
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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.FirebaseDatabase;

public class SignupTabFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    public float v = 0;
    SharedPreferences.Editor editor;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.singup_tab_fragment, container, false);

        TextInputLayout signupLayoutNameInput = root.findViewById(R.id.signupLayoutNameInput);
        TextInputLayout signupLayoutEmailInput = root.findViewById(R.id.signupLayoutEmailInput);
        TextInputLayout signupLayoutPasswordInput = root.findViewById(R.id.signupLayoutPasswordInput);
        EditText signup_name = root.findViewById(R.id.signup_name);
        EditText signup_number = root.findViewById(R.id.signup_number);
        EditText et_signup_email = root.findViewById(R.id.et_signup_email);
        EditText et_signup_password = root.findViewById(R.id.et_signup_password);

        Button btn_signup = root.findViewById(R.id.btn_signup);
        ProgressBar progressBar = root.findViewById(R.id.progressBar);

        firebaseAuth = FirebaseAuth.getInstance();

        signupLayoutNameInput.setTranslationX(300);
        signupLayoutEmailInput.setTranslationX(300);
        signupLayoutPasswordInput.setTranslationX(300);
        btn_signup.setTranslationX(300);

        signupLayoutNameInput.setAlpha(v);
        signupLayoutEmailInput.setAlpha(v);
        signupLayoutPasswordInput.setTranslationX(300);
        btn_signup.setAlpha(v);

        signupLayoutNameInput.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        signupLayoutEmailInput.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        signupLayoutPasswordInput.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        btn_signup.animate().translationX(0).alpha(1).setDuration(1000).setStartDelay(800).start();

        //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {

                    case R.id.btn_signup:
                        userRegister();
                        break;

                }
            }

            public void userRegister() {

                String name=signup_name.getText().toString().trim();
                String number=signup_number.getText().toString().trim();
                String email=et_signup_email.getText().toString().trim();
                String password=et_signup_password.getText().toString().trim();


                //checking validity

                if (name.isEmpty())
                {
                    signup_name.setError("Enter a name");
                    signup_name.requestFocus();
                    return;
                }
                //...............................

                if (number.isEmpty())
                {
                    signup_number.setError("Enter a valid number");
                    signup_number.requestFocus();
                    return;
                }
                //...............................

                if (email.isEmpty())
                {
                    et_signup_email.setError("Enter in valid email");
                    et_signup_email.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
                {
                    et_signup_email.setError("Enter a valid email address");
                    et_signup_email.requestFocus();
                    return;
                }
                //...............................
                if (password.isEmpty())
                {
                    et_signup_password.setError("Enter a password");
                    et_signup_password.requestFocus();
                    return;
                }
                if (password.length()<6)
                {
                    et_signup_password.setError("Minimum length of a password should be 6");
                    et_signup_password.requestFocus();
                    return;
                }
                //...............................


                progressBar.setVisibility(View.VISIBLE);

                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);

                                if (task.isSuccessful()) {

                                    User user = new User(number, email, password);
                                    FirebaseDatabase.getInstance().getReference("Users")
                                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {

                                                    if (task.isSuccessful()) {
                                                        // Sign in success, update UI with the signed-in user's information

                                                        Intent intent = new Intent(getActivity(),Navigation.class);
                                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                                        startActivity(intent);
                                                        editor.putString("saved_Name", name);
                                                        editor.putString("saved_Number", number);
                                                        editor.putString("saved_Email", email);
                                                        editor.putString("saved_Password", password);
                                                        editor.commit();
                                                        Toast.makeText(getActivity(),"Register is successful",Toast.LENGTH_SHORT).show();
                                                        getActivity().finish();

                                                    }

                                                }
                                            });
                                }else {
                                    // If sign in fails, display a message to the user.
                                    if (task.getException()instanceof FirebaseAuthUserCollisionException)
                                    {
                                        Toast.makeText(getActivity(),"This email is already registered",Toast.LENGTH_SHORT).show();
                                    }else {
                                        Toast.makeText(getActivity(),"Error : "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        });


            }

        });


        return root;



    }

}

