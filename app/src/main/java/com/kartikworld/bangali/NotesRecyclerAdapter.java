package com.kartikworld.bangali;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesRecyclerAdapter extends RecyclerView.Adapter<NotesRecyclerAdapter.ViewHolder> {

    // creating a variable for array list and context.
    private ArrayList<NoteAdapter> myNoteAdapters = new ArrayList();
    private final OnNoteListener myOnNoteListener;

    // creating a constructor for our variables.
    public NotesRecyclerAdapter(ArrayList<NoteAdapter> noteAdapters, OnNoteListener onNoteListener) {
        this.myNoteAdapters = noteAdapters;
        this.myOnNoteListener = onNoteListener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // below line is to inflate our layout.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view, myOnNoteListener);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // setting data to our views of recycler view.
        NoteAdapter modal = myNoteAdapters.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());

    }

    @Override
    public int getItemCount() {
        // returning the size of array list.
        return myNoteAdapters.size();

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        // creating variables for our views.
        private final TextView courseNameTV;
        private final TextView courseDescTV;
        OnNoteListener onNoteListener;

        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);

            // initializing our views with their ids.
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);

            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());
        }
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }


}
