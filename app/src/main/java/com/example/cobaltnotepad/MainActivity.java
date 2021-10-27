package com.example.cobaltnotepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RecyclerView cobalt_list;
    CobaltAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // TODO - Read from .json into NoteManager.note_collection
        cobalt_list = findViewById(R.id.cobalt_list);
        adapter = new CobaltAdapter(this, NoteManager.note_collection);
        cobalt_list.setAdapter(adapter);
        LinearLayoutManager layout_reverse = new LinearLayoutManager(this);
        layout_reverse.setReverseLayout(true);
        layout_reverse.setStackFromEnd(true);
        cobalt_list.setLayoutManager(layout_reverse);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cobalt_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            case R.id.note_new:
                NoteManager.set_note_curr(null);
                startActivity(new Intent(MainActivity.this, EditActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

}