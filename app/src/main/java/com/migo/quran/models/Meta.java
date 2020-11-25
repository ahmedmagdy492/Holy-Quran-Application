package com.migo.quran.models;

import androidx.annotation.Nullable;

public class Meta {
    private int current_page;
    private Integer next_page;
    private Integer prev_page;
    private int total_pages;
    private int total_count;

    public int getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(int current_page) {
        this.current_page = current_page;
    }

    public Integer getNext_page() {
        return next_page;
    }

    public void setNext_page(Integer next_page) {
        this.next_page = next_page;
    }

    public Integer getPrev_page() {
        return prev_page;
    }

    public void setPrev_page(Integer prev_page) {
        this.prev_page = prev_page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
