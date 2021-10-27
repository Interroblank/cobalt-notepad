package com.example.cobaltnotepad;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

// import java.util.Date;
// import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class CobaltAdapter extends RecyclerView.Adapter<CobaltAdapter.ViewHolder> {

    Context         con;
    ArrayList<Note> col;

    public CobaltAdapter(Context to_con, ArrayList<Note> to_col) {
        con = to_con;
        col = to_col;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(con);
        View v = inflater.inflate(R.layout.cobalt_note, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.note_head.setText(NoteManager.note_collection.get(position).get_head());
        if (NoteManager.note_collection.get(position).get_body().length() < 80) {
            holder.note_body.setText(NoteManager.note_collection.get(position).get_body());
        } else holder.note_body.setText(NoteManager.note_collection.get(position).get_body().substring(0, (80 - 1)) + " . . .");
        // SimpleDateFormat format_date = new SimpleDateFormat("dd/MM/yyyy");
        // SimpleDateFormat format_time = new SimpleDateFormat("hh:mm aa");
        holder.note_date.setText( // TODO - Format date and time.
                NoteManager.note_collection.get(position).get_date().toString() + "\n" +
                NoteManager.note_collection.get(position).get_time().toString()
                // format_date.format(NoteManager.note_collection.get(position).get_date()) + "\n" +
                // format_time.format(NoteManager.note_collection.get(position).get_time())
        );
    }

    @Override
    public int getItemCount() {
        return col.size();
    } // ?

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView note_head;
        TextView note_body;
        TextView note_date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            note_head = itemView.findViewById(R.id.note_head);
            note_body = itemView.findViewById(R.id.note_body);
            note_date = itemView.findViewById(R.id.note_date);
            // Tapping on a note in the list.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    NoteManager.set_note_curr_index(index);
                    NoteManager.set_note_curr(NoteManager.note_collection.get(index));
                    con.startActivity(new Intent(con, EditActivity.class));
                }
            });
            // Holding on a note in the list.
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(con);
                    alert_builder.setCancelable(false);
                    alert_builder.setMessage("Would you like to delete this note?");
                    alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int i) {
                            NoteManager.note_collection.remove(getAdapterPosition());
                            notifyDataSetChanged();
                            d.cancel();
                        }
                    });
                    alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface d, int i) {
                            d.cancel();
                        }
                    });
                    AlertDialog alert = alert_builder.create();
                    alert.show();
                    return true; // ?
                }
            });
        }

    }

}