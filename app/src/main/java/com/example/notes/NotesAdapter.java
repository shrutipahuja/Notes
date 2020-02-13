package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    LayoutInflater noteInflater;
    List<Note> notes;

    NotesAdapter(Context context, List<Note> notes) {
        this.noteInflater = LayoutInflater.from(context);
        this.notes = notes;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = noteInflater.inflate(R.layout.viewholder_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        String title = notes.get(position).getTitle();
        String content = notes.get(position).getContent();
        String date = notes.get(position).getDate();
        String time = notes.get(position).getTime();

        holder.noteTitle.setText(title);
        holder.noteContent.setText(content);
        holder.noteDate.setText(date);
        holder.noteTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent, noteDate, noteTime;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titleTextView);
            noteContent = itemView.findViewById(R.id.contentTextView);
            noteDate = itemView.findViewById(R.id.dateTextView);
            noteTime = itemView.findViewById(R.id.timeTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   // Toast.makeText(view.getContext(), "Click on note", Toast.LENGTH_LONG ).show();
                    Intent showDetailsIntent = new Intent(view.getContext(), NoteDetailsActivity.class);
                    Toast.makeText(view.getContext(), String.valueOf(notes.get(getAdapterPosition()).getId()), Toast.LENGTH_LONG).show();
                    showDetailsIntent.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(showDetailsIntent);
                }
            });
        }
    }
}
