package com.migo.quran.models;

public class MediaContent
{
    private String url;
    private String embed_text;
    private String provider;
    private String author_name;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getEmbed_text() {
        return embed_text;
    }

    public void setEmbed_text(String embed_text) {
        this.embed_text = embed_text;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }
}
