package com.example.guruchetansingh.lazyloading.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by guruchetansingh on 9/15/15.
 */
public class SetImageTask extends AsyncTask<String, Void, Boolean> {
    //    private ImageView imageview;
    private Bitmap bmp;
    private TCLruCache cache;
    private WeakReference<ImageView> imageViewWeakReference;

    public SetImageTask() {

    }

    public SetImageTask(ImageView imageView, TCLruCache cache) {
//        this.imageview = imageview;
        this.cache = cache;
        this.imageViewWeakReference = new WeakReference<ImageView>(imageView);


    }

    @Override
    protected Boolean doInBackground(String... params) {
        String url = params[0];
        try {
            bmp = getBitmapFromURL(url);
            if (bmp != null) {
                cache.put(url, bmp);
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            imageViewWeakReference.get().setImageBitmap(bmp);
        }
        super.onPostExecute(result);
    }

    private Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection
                    = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
