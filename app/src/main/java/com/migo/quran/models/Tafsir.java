package com.migo.quran.models;

import androidx.annotation.NonNull;

public class Tafsir {
    private int id;
    private String text;
    private int verse_id;
    private String language_name;
    private String resource_name;
    private String verse_key;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getVerse_id() {
        return verse_id;
    }

    public void setVerse_id(int verse_id) {
        this.verse_id = verse_id;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public String getResource_name() {
        return resource_name;
    }

    public void setResource_name(String resource_name) {
        this.resource_name = resource_name;
    }

    public String getVerse_key() {
        return verse_key;
    }

    public void setVerse_key(String verse_key) {
        this.verse_key = verse_key;
    }

    @NonNull
    @Override
    public String toString() {
        return this.text + " \n " + this.resource_name;
    }
}
