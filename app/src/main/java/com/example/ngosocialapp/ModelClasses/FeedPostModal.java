package com.example.ngosocialapp.ModelClasses;

import android.net.Uri;

public class FeedPostModal {
    private Uri postMedia;

    private String ngoName,captions;

    public FeedPostModal(){}
    public FeedPostModal(Uri postMedia, String ngoName, String captions) {
        this.postMedia = postMedia;

        this.ngoName = ngoName;
        this.captions = captions;
    }

    public Uri getPostMedia() {
        return postMedia;
    }

    public void setPostMedia(Uri postMedia) {
        this.postMedia = postMedia;
    }


    public String getNgoName() {
        return ngoName;
    }

    public void setNgoName(String ngoName) {
        this.ngoName = ngoName;
    }

    public String getCaptions() {
        return captions;
    }

    public void setCaptions(String captions) {
        this.captions = captions;
    }
}
