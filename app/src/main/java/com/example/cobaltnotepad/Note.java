package com.example.cobaltnotepad;

import java.time.LocalDate;
import java.time.LocalTime;

public class Note {

    private String note_head;
    private String note_body;
    private LocalDate date;
    private LocalTime time;

    public Note(String to_head, String to_body) {
        note_head = to_head;
        note_body = to_body;
    }

    public String get_head() {
        return note_head;
    }

    public void set_head(String note_head) {
        this.note_head = note_head;
    }

    public String get_body() {
        return note_body;
    }

    public void set_body(String note_body) {
        this.note_body = note_body;
    }

    public LocalDate get_date() {
        return date;
    }

    public void set_date(LocalDate date) {
        this.date = date;
    }

    public LocalTime get_time() {
        return time;
    }

    public void set_time(LocalTime time) {
        this.time = time;
    }

}