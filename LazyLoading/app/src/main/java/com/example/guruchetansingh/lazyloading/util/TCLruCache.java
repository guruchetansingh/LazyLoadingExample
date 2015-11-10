package com.example.guruchetansingh.lazyloading.util;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by guruchetansingh on 9/15/15.
 */
public class TCLruCache extends LruCache<String, Bitmap> {

    public TCLruCache(int maxSize) {
        super(maxSize);
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        int kbOfBitmap = value.getByteCount() / 1024;
        return kbOfBitmap;
    }
}