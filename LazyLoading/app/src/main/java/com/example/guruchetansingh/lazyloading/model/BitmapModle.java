package com.example.guruchetansingh.lazyloading.model;

import android.graphics.Bitmap;

import com.example.guruchetansingh.lazyloading.util.Constants;


/**
 *
 *
 * Created by guruchetansingh on 9/11/15.
 */
public class BitmapModle implements Constants {
    private Bitmap bitmap;
    private String url;
    private int downloadingStatus=Constants.NONE;

    public BitmapModle() {
    }

    public BitmapModle(Bitmap bitmap, String url) {
        this.bitmap = bitmap;
        this.url = url;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    public int getDownloadingStatus() {
        return downloadingStatus;
    }

    public void setDownloadingStatus(int downloadingStatus) {
        this.downloadingStatus = downloadingStatus;
    }
}
