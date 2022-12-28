package com.kartikworld.bangali;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyViewHolder> {
    private List<MovieModel> moviesList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, newsCat, newsDes;
        Button button;
        MyViewHolder(View myView) {
            super(myView);
            newsTitle = myView.findViewById(R.id.newsTitle);
            newsDes = myView.findViewById(R.id.newsDes);
            newsCat = myView.findViewById(R.id.newsCat);
            button = myView.findViewById(R.id.buttonDetails);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    button.setText("button");


                }
            });
        }
    }
    public MoviesAdapter(List<MovieModel> moviesList) {
        this.moviesList = moviesList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View myView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movies_list, parent, false);


        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        MovieModel movie = moviesList.get(position);

        holder.newsTitle.setText(movie.getTitle());
        holder.newsDes.setText(movie.getDes());
        holder.newsCat.setText(movie.getCat());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
