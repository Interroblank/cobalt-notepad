package com.example.cobaltnotepad;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;

import java.time.LocalDate;
import java.time.LocalTime;

public class EditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        if (NoteManager.get_note_curr() != null) {
            EditText to_head = findViewById(R.id.edit_head);
            EditText to_body = findViewById(R.id.edit_body);
            to_head.setText(NoteManager.get_note_curr().get_head());
            to_body.setText(NoteManager.get_note_curr().get_body());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.save:
                try {
                    save();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean save() throws JSONException {
        EditText to_head = findViewById(R.id.edit_head);
        EditText to_body = findViewById(R.id.edit_body);
        Note to_save = new Note(
                to_head.getText().toString(),
                to_body.getText().toString());
        to_save.set_date(LocalDate.now()); // TODO - Newer version than target SDK, apparently.
        to_save.set_time(LocalTime.now()); // TODO - Newer version than target SDK, apparently.
        // Are we making a new note or editing an already existing one?
        if (NoteManager.get_note_curr() != null) {
            NoteManager.note_collection.set(NoteManager.get_note_curr_index(), to_save);
            Toast.makeText(this, "Your note has been saved.", Toast.LENGTH_SHORT).show();
            // Write to .json after each save.
            JSONManager.json_save();
            return true;
        } NoteManager.note_collection.add(to_save);
        int index = NoteManager.note_collection.size() - 1;
        NoteManager.set_note_curr_index(index);
        NoteManager.set_note_curr(NoteManager.note_collection.get(index));
        Toast.makeText(this, "Your note has been saved.", Toast.LENGTH_SHORT).show();
        // Write to .json after each save.
        JSONManager.json_save();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (NoteManager.get_note_curr() == null) {
            AlertDialog.Builder alert_builder = new AlertDialog.Builder(this);
            alert_builder.setCancelable(false);
            alert_builder.setMessage("Would you like to save this note?");
            alert_builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.O)
                @Override
                public void onClick(DialogInterface d, int i) {
                    d.cancel();
                    try {
                        save();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finish();
                }
            });
            alert_builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface d, int i) {
                    d.cancel();
                    finish();
                }
            });
            AlertDialog alert = alert_builder.create();
            alert.show();
        }
        else {
            finish(); // Will have to manually save otherwise.
        }
    }

}