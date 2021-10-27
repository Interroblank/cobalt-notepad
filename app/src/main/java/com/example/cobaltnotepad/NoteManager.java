package com.example.cobaltnotepad;

import java.util.ArrayList;

public class NoteManager {

    // The dreaded global variable! Not good practice, but I'm doing it this way for now.
    public static ArrayList<Note> note_collection = new ArrayList<Note>();
    private static int note_curr_index;
    private static Note note_curr;

    private NoteManager() {}

    public static int get_note_curr_index() {
        return note_curr_index;
    }

    public static void set_note_curr_index(int note_curr_index) {
        NoteManager.note_curr_index = note_curr_index;
    }

    public static Note get_note_curr() {
        return note_curr;
    }

    public static void set_note_curr(Note note_curr) {
        NoteManager.note_curr = note_curr;
    }

}