package com.example.ngosocialapp.ModelClasses;

import android.net.Uri;

public class NgoProfileAllPhotoModel {
    Uri ngoPost;
    public NgoProfileAllPhotoModel(){}
    public NgoProfileAllPhotoModel(Uri ngoPost) {
        this.ngoPost = ngoPost;
    }

    public Uri getNgoPost() {
        return ngoPost;
    }

    public void setNgoPost(Uri ngoPost) {
        this.ngoPost = ngoPost;
    }
}
