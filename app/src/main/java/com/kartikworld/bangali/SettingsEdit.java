package com.kartikworld.bangali;

import static android.content.ContentValues.TAG;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.PicassoProvider;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;


public class SettingsEdit extends AppCompatActivity {

    public FirebaseAuth firebaseAuth;
    public FirebaseFirestore firebaseFirestore;
    public FirebaseUser firebaseUser;
    StorageReference storageReference;
    String userId;
    Uri imageUri;
    TextView tvName,tvNumber,tvEmail;
    CardView cardEmail, cardLogout;
    CircleImageView imgProfile;
    ProgressBar progressBar;
    RelativeLayout changePassword;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    PreferenceManager preferenceManager;
    Intent camera;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_settings);
        StrictMode.VmPolicy.Builder builder=new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());


        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);
        tvNumber = findViewById(R.id.tvNumber);
        cardEmail = findViewById(R.id.cardEmail);
        changePassword = findViewById(R.id.changePassword);
        imgProfile = findViewById(R.id.imgProfile);
        cardLogout = findViewById(R.id.cardLogout);
        progressBar = findViewById(R.id.progressBar);
        preferenceManager=PreferenceManager.getInstance(this);


        sharedPreferences = getSharedPreferences("MySharedPreferences", MODE_PRIVATE);
        editor = sharedPreferences.edit();




        firebaseAuth = FirebaseAuth.getInstance();
        userId=firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();

        // Create a Cloud Storage reference from the app
        storageReference= FirebaseStorage.getInstance().getReference();

        String name = sharedPreferences.getString("saved_Name","Name : Xyz");
        String email = sharedPreferences.getString("saved_Email","Email : xyz@gmail.com");
        String number = sharedPreferences.getString("saved_Number","Number : 0170000000");

        tvName.setText(name);
        tvEmail.setText(email);
        tvNumber.setText(number);

        preferenceManager=PreferenceManager.getInstance(this);

        String previouslyEncodedImage = preferenceManager.getString("image_data");

        if( !previouslyEncodedImage.equalsIgnoreCase("") ) {
            byte[] b = Base64.decode(previouslyEncodedImage, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(b, 0, b.length);
            imgProfile.setImageBitmap(bitmap);
        }
        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Edit Profile");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------
        //*************************************************************************************************
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( SettingsEdit.this, ResetPassword.class));
            }
        });
        //******************************************************************************************************

        StorageReference Img=storageReference.child("users/"+firebaseAuth.getCurrentUser().getEmail()+"/profile.jpg");
        Img.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {

                Picasso.get().load(uri).networkPolicy(NetworkPolicy.OFFLINE).into(imgProfile);

            }
        });

        //*************************************************************************
        //*************************************************************************
        //*************************************************************************
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showImageImportDialog();

            }

            private void showImageImportDialog() {
                //Options or Items displayed in dialog once it is clicked

                String[] items = {"Camera", "Gallery"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(SettingsEdit.this);

                //Set TITLE
                dialog.setTitle("Select Image");

                dialog.setItems(items, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (which == 0) {
                            //Camera Option Clicked
                            imgCamera();

                        }

                        if (which == 1) {
                            //Gallery Option Clicked
                            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                            startActivityForResult(gallery, 1000);
                        }
                    }

                    private void imgCamera() {
                        if((ActivityCompat.checkSelfPermission(
                                SettingsEdit.this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED)){
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                requestPermissions(new String[]{
                                        Manifest.permission.CAMERA,
                                },123);
                            }
                        }
                        else{
                            camera=new Intent();
                            camera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                            startActivityForResult(camera,118);
                        }
                    }


                });
                dialog.create().show(); //SHOW DIALOG
            }

        });
        //*************************************************************************
        //*************************************************************************
        //*************************************************************************




    }

    //**********************************************************************************************
    //**********************************************************************************************

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        progressBar.setVisibility(View.VISIBLE);


          if(requestCode==118 && resultCode==RESULT_OK){
              if (requestCode==118) {

                  obCaptureImageResult(data);

              }

        } else if (requestCode==1000 && resultCode==RESULT_OK){

              imageUri=data.getData();
              //imgProfile.setImageURI(imageUri);
              uploadImageFirebase(imageUri);

              Toast.makeText(this, "Saving image........................\nPlease wait until completion", Toast.LENGTH_LONG).show();

          }
           else{
                  Toast.makeText(this, "Image not found", Toast.LENGTH_SHORT).show();
                  progressBar.setVisibility(View.GONE);
                }
    }

    //00000000000000000000000000000000000000000000000000
    private void obCaptureImageResult(Intent data) {
        Bitmap photo = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        byte[] b = bytes.toByteArray();
        imgProfile.setImageBitmap(photo);
        uploadCameraToFirebase(b);
        String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);
        preferenceManager.setString("image_data",encodedImage);
    }
    //00000000000000000000000000000000000000000000000000

    //++++++++++++++++++++++++++++++++++++++++
    private void uploadCameraToFirebase(byte[] b) {

        StorageReference fileRef=storageReference.child("users/"+firebaseAuth.getCurrentUser().getEmail()+"/profile.jpg");
        fileRef.putBytes(b).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressBar.setVisibility(View.GONE);
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Successfully Downloaded
                        progressBar.setVisibility(View.GONE);

                        Picasso.get().load(uri).into(imgProfile);
                        Toast.makeText(SettingsEdit.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                    }


                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(SettingsEdit.this,"Fail to image uploaded",Toast.LENGTH_SHORT).show();

            }
        });

    }
    //++++++++++++++++++++++++++++++++++++++++




    private void uploadImageFirebase(Uri imageUri) {

        //Upload Image To Firebase
        progressBar.setVisibility(View.VISIBLE);

        StorageReference fileRef=storageReference.child("users/"+firebaseAuth.getCurrentUser().getEmail()+"/profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        //Successfully Downloaded
                        progressBar.setVisibility(View.GONE);

                            Picasso.get().load(uri).into(imgProfile);
                            Toast.makeText(SettingsEdit.this,"Image uploaded",Toast.LENGTH_SHORT).show();
                        }


                });
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressBar.setVisibility(View.GONE);

                Toast.makeText(SettingsEdit.this,"Fail to image uploaded",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
//**********************************************************************************************
//**********************************************************************************************