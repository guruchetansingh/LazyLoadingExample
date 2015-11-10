package com.example.guruchetansingh.lazyloading.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.example.guruchetansingh.lazyloading.R;
import com.example.guruchetansingh.lazyloading.model.BitmapModle;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by guruchetansingh on 9/12/15.
 */
public class ImageDownloaderAsynTask extends AsyncTask<BitmapModle, Integer, Bitmap> implements Constants {

    private Activity activity;
    private WeakReference<ImageView> imageViewWeakReference;

    public ImageDownloaderAsynTask(Activity activity, ImageView imageView) {
        this.activity = activity;
        this.imageViewWeakReference = new WeakReference<ImageView>(imageView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(BitmapModle... params) {
        Bitmap bitmap = null;
        if (params[0].getUrl() != null) {
            params[0].setDownloadingStatus(Constants.STARTED);
            params[0].setDownloadingStatus(Constants.PENDING);
//            if (params[0].getBitmap() == null) {
                bitmap = downloadImage(params[0].getUrl());
                if (bitmap != null) {
//                    params[0].setBitmap(bitmap);
                    params[0].setDownloadingStatus(Constants.COMPLETED);
                } else {
                    params[0].setDownloadingStatus(Constants.NONE);
                }
            }
//        }
        return bitmap;
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);
    }


    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        Log.d(TAG, "onPostExecute ");
        imageViewWeakReference.get().setImageBitmap(bitmap);
//        imageViewWeakReference.get().invalidate();
        imageViewWeakReference.get().refreshDrawableState();
        bitmap.recycle();
    }


    private Bitmap downloadImage(String url) {
        Log.d(TAG, "url = " + url);
        try {
            return BitmapFactory.decodeStream((InputStream) new URL(url).getContent());
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG, "e = " + e.toString());

            return BitmapFactory.decodeResource(activity.getResources(), R.drawable.no);

        }
    }










}
