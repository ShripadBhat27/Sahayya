package com.example.ngosocialapp;

import android.net.Uri;

public class Post {
    String postUrl;
    String caption;
    String ngoName;
    public  Post(){}
    public Post(String postUrl, String caption, String ngoName) {
        this.postUrl = postUrl;
        this.caption = caption;
        this.ngoName = ngoName;
    }

    public String getPostUrl() {
        return postUrl;
    }

    public void setPostUrl(String postUrl) {
        this.postUrl = postUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }
}
