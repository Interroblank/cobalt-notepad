package com.example.cobaltnotepad;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONManager {

    public static void json_pull() {
        // TODO - .json functionality.
    }

    public static void json_save() throws JSONException {
        JSONObject to_json = new JSONObject();
        JSONArray to_json_arr = new JSONArray();
        for (int i = 0; i < NoteManager.note_collection.size(); i++) {
            to_json_arr.put(NoteManager.note_collection.get(i));
        } to_json.put("note_collection", to_json_arr);
        try {
            FileWriter file_json = new FileWriter("note_collection.json");
            file_json.write(to_json.toString());
            file_json.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
