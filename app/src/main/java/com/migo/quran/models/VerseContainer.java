package com.migo.quran.models;

import java.util.ArrayList;

public class VerseContainer {
    private ArrayList<Verse> verses;
    private Meta meta;

    public ArrayList<Verse> getVerses() {
        return verses;
    }

    public void setVerses(ArrayList<Verse> verses) {
        this.verses = verses;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
