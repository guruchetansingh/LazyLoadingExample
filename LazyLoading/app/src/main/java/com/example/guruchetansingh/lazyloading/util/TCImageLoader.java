package com.example.guruchetansingh.lazyloading.util;

import android.app.ActivityManager;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.example.guruchetansingh.lazyloading.model.BitmapModle;

import java.lang.ref.WeakReference;

/**
 * Created by guruchetansingh on 9/15/15.
 */
public class TCImageLoader implements ComponentCallbacks2,Constants {
    private TCLruCache cache;

    public TCImageLoader(Context context,TCLruCache cache) {
        ActivityManager am = (ActivityManager) context.getSystemService(
                Context.ACTIVITY_SERVICE);
        int maxKb = am.getMemoryClass() * 1024;
        int limitKb = maxKb / 8; // 1/8th of total ram
        this.cache = cache;//new TCLruCache(limitKb);
    }

    public void display( String url, final ImageView imageView,int defaultresource) {
//        WeakReference<ImageView> imageViewWeakReference=new WeakReference<ImageView>(imageView);
        imageView.setImageResource(defaultresource);
        //String url = ((BitmapModle) imageViewWeakReference.get().getTag()).getUrl();
        Bitmap image = cache.get(url);

        if (image != null) {

                imageView.setImageBitmap(image);

        } else {
//            new WeakReference<SetImageTask>(
//                    new SetImageTask(imageView, cache)).get().execute(url);
            try {
                ((WeakReference<SetImageTask>) imageView.getTag()).get().execute(url);
            }catch (Exception e)
            {
                Log.e(TAG,e.toString());
            }

        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
    }

    @Override
    public void onLowMemory() {
    }

    @Override
    public void onTrimMemory(int level) {
        if (level >= TRIM_MEMORY_MODERATE) {
            cache.evictAll();
        } else if (level >= TRIM_MEMORY_BACKGROUND) {
            cache.trimToSize(cache.size() / 2);
        }
    }
}