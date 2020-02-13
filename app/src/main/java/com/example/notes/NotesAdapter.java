package com.example.notes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Adapter class for data binding to the recycler view
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    private final LayoutInflater noteInflater;
    private final List<Note> notes;

    /**
     * Constructor for initialising the adapter
     *
     * @param context Context
     * @param notes   List<Note>
     */
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

    /**
     * Binding the contents of the view holder to the recycler view
     *
     * @param holder   NoteViewHolder
     * @param position int
     */
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

    /**
     * Returns the size of the list
     *
     * @return int
     */
    @Override
    public int getItemCount() {
        return notes.size();
    }

    /**
     * ViewHolder class for each view item
     */
    class NoteViewHolder extends RecyclerView.ViewHolder {
        final TextView noteTitle, noteContent, noteDate, noteTime;

        NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            noteTitle = itemView.findViewById(R.id.titleTextView);
            noteContent = itemView.findViewById(R.id.contentTextView);
            noteDate = itemView.findViewById(R.id.dateTextView);
            noteTime = itemView.findViewById(R.id.timeTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent showDetailsIntent = new Intent(view.getContext(), NoteDetailsActivity.class);
                    showDetailsIntent.putExtra("ID", notes.get(getAdapterPosition()).getId());
                    view.getContext().startActivity(showDetailsIntent);
                }
            });
        }
    }
}
