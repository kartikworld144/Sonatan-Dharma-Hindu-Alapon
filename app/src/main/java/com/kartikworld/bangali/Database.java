package com.kartikworld.bangali;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends AppCompatActivity {

    EditText etName, etMobile, etEmail;
    Button btnUpload;
    ListView listView;
    LottieAnimationView progressBar;
    HashMap <String,String> hashMap;
    ArrayList <HashMap <String,String>> arrayList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.database);

        etName = findViewById(R.id.etName);
        etMobile = findViewById(R.id.etMobile);
        etEmail = findViewById(R.id.etEmail);
        btnUpload = findViewById(R.id.btnUpload);
        progressBar=findViewById(R.id.progressBar);
        listView = findViewById(R.id.listView);
        loadData();




                btnUpload.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String title = etName.getText().toString();
                        String link = etMobile.getText().toString();
                        String description = etEmail.getText().toString();
                        String url = "https://www.kartikworld.com/apps/phpMyAdmin/youtube_data.php?a=" + title +
                                "&b=" + link + "&c=" + description;
                        //checking validity
                        if (title.isEmpty()) {
                            etName.setError("আগে লিখুন");
                            etName.requestFocus();
                            return;
                        }

                        if (link.isEmpty()) {
                            etMobile.setError("আগে লিখুন");
                            etMobile.requestFocus();
                            return;
                        }

                        /*if (description.isEmpty()) {
                            etEmail.setError("আগে লিখুন");
                            etEmail.requestFocus();
                            return;
                           }
                         */
                         else {
                            progressBar.setVisibility(View.VISIBLE);

                            StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    progressBar.setVisibility(View.GONE);

                                    new AlertDialog.Builder(Database.this)
                                            .setTitle("It's working very good")
                                            .setMessage(response)
                                            .setPositiveButton("OK",
                                                    new DialogInterface.OnClickListener() {

                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            Toast.makeText(Database.this, "Ok,Upload Done\nServer Received", Toast.LENGTH_SHORT).show();
                                                        }
                                                    })
                                            .show();

                                    loadData();

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(View.GONE);
                                    //Database.super.onBackPressed();

                                }

                            });

                            RequestQueue requestQueue = Volley.newRequestQueue(Database.this);
                            requestQueue.add(stringRequest);

                            // String Add and Clear Text
                            etName.getText().clear();
                            etMobile.getText().clear();
                            etEmail.getText().clear();

                        }
                    }
                });
    }

    //************************************

    public class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View myView = layoutInflater.inflate(R.layout.item_data, null);
            TextView tvId = myView.findViewById(R.id.tvId);
            TextView tvName = myView.findViewById(R.id.tvName);
            TextView tvMobile = myView.findViewById(R.id.tvMobile);
            TextView tvEmail = myView.findViewById(R.id.tvEmail);
            Button btUpdate = myView.findViewById(R.id.btUpdate);
            Button btDelete = myView.findViewById(R.id.btDelete);

            hashMap = arrayList.get(position);
            String id = hashMap.get("id");
            String title = hashMap.get("title");
            String link = hashMap.get("link");
            String description = hashMap.get("description");

            tvId.setText(id);
            tvName.setText(title);
            tvMobile.setText(link);
            tvEmail.setText(description);

            //++++++++++++++++++++++++++++
            btUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String title = etName.getText().toString();
                    String link = etMobile.getText().toString();
                    String description = etEmail.getText().toString();
                    String url = "https://kartikworld.com/apps/phpMyAdmin/update.php?id="+ id+"&title="+title+"&link="+link
                            +"&description="+description;

                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            new AlertDialog.Builder(Database.this)
                                    .setTitle("It's working very nice")
                                    .setMessage(response)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(Database.this, "Update & Save Success", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                    .show();

                            loadData();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.VISIBLE);
                            //Database.super.onBackPressed();

                        }

                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(Database.this);
                    requestQueue.add(request);

                    // String Add and Clear Text
                    etName.getText().clear();
                    etMobile.getText().clear();
                    etEmail.getText().clear();


                }
            });
            //+++++++++++++++++++++++++++++
            btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String url = "https://kartikworld.com/apps/phpMyAdmin/delete.php?id="+ id;

                    progressBar.setVisibility(View.VISIBLE);

                    StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressBar.setVisibility(View.GONE);

                            new AlertDialog.Builder(Database.this)
                                    .setTitle("It's working very nice")
                                    .setMessage(response)
                                    .setPositiveButton("OK",
                                            new DialogInterface.OnClickListener() {

                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Toast.makeText(Database.this, "Delete Success", Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                    .show();

                            loadData();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressBar.setVisibility(View.VISIBLE);
                            //Database.super.onBackPressed();

                        }

                    });

                    RequestQueue requestQueue = Volley.newRequestQueue(Database.this);
                    requestQueue.add(request);

                    // String Add and Clear Text
                    etName.getText().clear();
                    etMobile.getText().clear();
                    etEmail.getText().clear();

                }
            });

            return myView;

        }
    }
    //+++++++++++++++++++++++++++++++++++++++
    public void loadData(){
        arrayList = new ArrayList<>();
        RequestQueue requestQueue = Volley.newRequestQueue(Database.this);

        progressBar.setVisibility(View.VISIBLE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://www.kartikworld.com/apps/phpMyAdmin/youtube_view.php",
                null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                for (int x = 0; x<response.length();x++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(x);

                        String id = jsonObject.getString("id");
                        String title = jsonObject.getString("title");
                        String link = jsonObject.getString("link");
                        String description = jsonObject.getString("description");

                        hashMap=new HashMap<>();
                        hashMap.put("id",id);
                        hashMap.put("title",title);
                        hashMap.put("link",link);
                        hashMap.put("description",description);
                        arrayList.add(hashMap);


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                //***************************
                if (arrayList.size()>0){
                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ServerResponseCheck", error.toString());

            }
        });
        requestQueue.add(jsonArrayRequest);

    }


}