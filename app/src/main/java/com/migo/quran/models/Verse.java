package com.migo.quran.models;

import java.util.ArrayList;

public class Verse {
    private int id;
    private int verse_number;
    private int chapter_id;
    private String verse_key;
    private String text_madani;
    private String text_indopak;
    private String text_simple;
    private int juz_number;
    private int hizb_number;
    private int rub_number;
    private String sajdah;
    private String sajdah_number;
    private int page_number;
    private Audio audio;
    private ArrayList<Translation> translations;
    private ArrayList<MediaContent> media_contents;
    private ArrayList<Word> words;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVerse_number() {
        return verse_number;
    }

    public void setVerse_number(int verse_number) {
        this.verse_number = verse_number;
    }

    public int getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(int chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getVerse_key() {
        return verse_key;
    }

    public void setVerse_key(String verse_key) {
        this.verse_key = verse_key;
    }

    public String getText_madani() {
        return text_madani;
    }

    public void setText_madani(String text_madani) {
        this.text_madani = text_madani;
    }

    public String getText_indopak() {
        return text_indopak;
    }

    public void setText_indopak(String text_indopak) {
        this.text_indopak = text_indopak;
    }

    public String getText_simple() {
        return text_simple;
    }

    public void setText_simple(String text_simple) {
        this.text_simple = text_simple;
    }

    public int getJuz_number() {
        return juz_number;
    }

    public void setJuz_number(int juz_number) {
        this.juz_number = juz_number;
    }

    public int getHizb_number() {
        return hizb_number;
    }

    public void setHizb_number(int hizb_number) {
        this.hizb_number = hizb_number;
    }

    public int getRub_number() {
        return rub_number;
    }

    public void setRub_number(int rub_number) {
        this.rub_number = rub_number;
    }

    public String getSajdah() {
        return sajdah;
    }

    public void setSajdah(String sajdah) {
        this.sajdah = sajdah;
    }

    public String getSajdah_number() {
        return sajdah_number;
    }

    public void setSajdah_number(String sajdah_number) {
        this.sajdah_number = sajdah_number;
    }

    public int getPage_number() {
        return page_number;
    }

    public void setPage_number(int page_number) {
        this.page_number = page_number;
    }

    public Audio getAudio() {
        return audio;
    }

    public void setAudio(Audio audio) {
        this.audio = audio;
    }

    public ArrayList<Translation> getTranslations() {
        return translations;
    }

    public void setTranslations(ArrayList<Translation> translations) {
        this.translations = translations;
    }

    public ArrayList<MediaContent> getMedia_contents() {
        return media_contents;
    }

    public void setMedia_contents(ArrayList<MediaContent> media_contents) {
        this.media_contents = media_contents;
    }

    public ArrayList<Word> getWords() {
        return words;
    }

    public void setWords(ArrayList<Word> words) {
        this.words = words;
    }
}
