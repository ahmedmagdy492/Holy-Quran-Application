package com.migo.quran.models;

import java.util.ArrayList;

public class Audio {
    private String url;
    private int duration;
    private ArrayList<ArrayList<String>> segments;
    private String format;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ArrayList<ArrayList<String>> getSegments() {
        return segments;
    }

    public void setSegments(ArrayList<ArrayList<String>> segments) {
        this.segments = segments;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
