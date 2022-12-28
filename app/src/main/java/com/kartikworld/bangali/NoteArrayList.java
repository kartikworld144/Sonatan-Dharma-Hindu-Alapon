package com.kartikworld.bangali;
import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class NoteArrayList extends AppCompatActivity implements NotesRecyclerAdapter.OnNoteListener {

    // creating variables for our ui components.
    private EditText courseNameEdt, courseDescEdt;
    private Button addButton,idBtnDelete;
    private RecyclerView recyclerView;
    // variable for our adapter class and array list
    private ArrayList<NoteAdapter> myNoteAdapter =new ArrayList<>();
    private NotesRecyclerAdapter adapter;
    private ArrayList<NoteAdapter> noteAdapterArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_arraylist);

        // initializing our variables.
        courseNameEdt = findViewById(R.id.idEdtCourseName);
        courseDescEdt = findViewById(R.id.idEdtCourseDescription);
        addButton = findViewById(R.id.idBtnAdd);
        idBtnDelete = findViewById(R.id.idBtnDelete);
        recyclerView = findViewById(R.id.recyclerView);


        //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
        // add toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("আপনার সুইটি নোট রাখুন");

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        // don't forget click listener for back button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(arrow -> onBackPressed());
        //-------------------------------------------------------------------------------------------------


        // calling method to load data // from shared prefs.// calling method to build // recycler view.
        loadData();
        myRecyclerView();

        // on click listener for adding data to array list.
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = courseNameEdt.getText().toString().trim();
                String description = courseDescEdt.getText().toString().trim();
                String url = "https://www.kartikworld.com/apps/phpMyAdmin/my_data.php?a="+title+
                        "&b="+description+"&c=";

                //checking validity
                if (title.isEmpty()) {
                    courseNameEdt.setError("আগে শিরোনাম লিখুন");
                    courseNameEdt.requestFocus();
                    return;
                }

                if (description.isEmpty()) {
                    courseDescEdt.setError("আগে বিবরণ লিখুন");
                    courseDescEdt.requestFocus();

                }
                else {
                    // below line is use to add data to array list.
                    noteAdapterArrayList.add(new NoteAdapter(courseNameEdt.getText().toString(), courseDescEdt.getText().toString()));
                    // notifying adapter when new data added.
                    adapter.notifyItemInserted(noteAdapterArrayList.size());
                    // on click listener for saving data in shared preferences.
                    saveData();
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            new AlertDialog.Builder(NoteArrayList.this)
                                    .setTitle("It's working very good")
                                    .setMessage(response)
                                    .setPositiveButton( "Ok",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(NoteArrayList.this, "Back", Toast.LENGTH_SHORT).show();
                                                    NoteArrayList.super.onBackPressed();
                                                }
                                            })
                                    .show();

                        }
                    },new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error){
                            //progressBar.setVisibility(View.GONE);

                        }

                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(NoteArrayList.this);
                    requestQueue.add(stringRequest);

                    // String Add and Clear Text
                    courseNameEdt.getText().clear();
                    courseDescEdt.getText().clear();

                }
            }
        });


        //******************************************************************************
        idBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyItemInserted(noteAdapterArrayList.size());
                Toast.makeText(NoteArrayList.this, "Delete", Toast.LENGTH_SHORT).show();

            }

        });
        //******************************************************************************

    }



    //******************************************************************************
    private void myRecyclerView() {
        // initializing our adapter class.
        adapter = new NotesRecyclerAdapter(noteAdapterArrayList, NoteArrayList.this);

        // adding layout manager to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);

        // setting layout manager to our recycler view.
        recyclerView.setLayoutManager(manager);

        // setting adapter to our recycler view.
        recyclerView.setAdapter(adapter);
    }
    //******************************************************************************



    //******************************************************************************
    private void loadData() {
        // method to load arraylist from shared prefs // initializing our shared prefs with name as // shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("courses", null);

        // below line is to get the type of our array list.
        Type type = new TypeToken<ArrayList<NoteAdapter>>() {}.getType();

        // in below line we are getting data from gson // and saving it to our array list
        noteAdapterArrayList = gson.fromJson(json, type);

        // checking below if the array list is empty or not
        if (noteAdapterArrayList == null) {
            // if the array list is empty // creating a new array list.
            noteAdapterArrayList = new ArrayList<>();
        }
    }
    //******************************************************************************




    //******************************************************************************
    private void saveData() {
        // method for saving the data in array list.// creating a variable for storing data in// shared preferences.
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);

        // creating a variable for editor to// store data in shared preferences.
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // creating a new variable for gson.
        Gson gson = new Gson();

        // getting data from gson and storing it in a string.
        String json = gson.toJson(noteAdapterArrayList);

        // below line is to save data in shared// prefs in the form of string.
        editor.putString("courses", json);

        // below line is to apply changes// and save data in shared prefs.
        editor.apply();

        // after saving data we are displaying a toast message.
        Toast.makeText(this, "তথ্য সংরক্ষণ সফল হয়েছে", Toast.LENGTH_SHORT).show();
    }
    //******************************************************************************
    //******************************************************************************

    @Override
    public void onNoteClick(int position) {
        Log.d(TAG,"Wow"+position);
        Toast.makeText(this, "Item No "+(1+position), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(NoteArrayList.this,NoteActivity_2.class);
        startActivity(intent);
        noteAdapterArrayList.remove(position);

        //NoteActivity_2.TEXT = courses;

    }

}
