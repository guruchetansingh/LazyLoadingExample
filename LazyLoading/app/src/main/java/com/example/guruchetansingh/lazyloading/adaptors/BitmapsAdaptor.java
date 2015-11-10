package com.example.guruchetansingh.lazyloading.adaptors;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guruchetansingh.lazyloading.R;
import com.example.guruchetansingh.lazyloading.model.BitmapModle;
import com.example.guruchetansingh.lazyloading.util.Constants;
import com.example.guruchetansingh.lazyloading.util.SetImageTask;
import com.example.guruchetansingh.lazyloading.util.TCImageLoader;
import com.example.guruchetansingh.lazyloading.util.TCLruCache;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by guruchetansingh on 9/11/15.
 */
public class BitmapsAdaptor extends BaseAdapter implements Constants {
    private  TCLruCache cache;
    private Activity activity;
    private ArrayList<BitmapModle> list = new ArrayList<BitmapModle>();
    private LayoutInflater layoutInflater;
    private  TCImageLoader tcImageLoader;

    public BitmapsAdaptor(Activity activity, ArrayList<BitmapModle> list) {
        this.list = list;
        this.activity = activity;
        this.layoutInflater = LayoutInflater.from(activity);

        ActivityManager am = (ActivityManager) activity.getSystemService(
                Context.ACTIVITY_SERVICE);
        int maxKb = am.getMemoryClass() * 1024;
        int limitKb = maxKb / 8; // 1/8th of total ram
        this.cache = new TCLruCache(limitKb);
        this.tcImageLoader=new TCImageLoader(activity,cache);
    }

    @Override
    public int getCount() {

        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.list_row_bitmap, null);
            viewHolder.bitmapImageView = (ImageView) view.findViewById(R.id.image_view_bitmap);
            viewHolder.urlTextView = (TextView) view.findViewById(R.id.text_view_url);
            view.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        BitmapModle bitmapModle = list.get(position);
//        if (bitmapModle.getDownloadingStatus() == Constants.NONE) {
        // TODO: 9/12/15 start downloading bitmap
//          bitmapModle.setDownloadingStatus(Constants.STARTED);
//            WeakReference<ImageDownloaderAsynTask> taskWeakReference = new WeakReference<ImageDownloaderAsynTask>(new ImageDownloaderAsynTask(activity, viewHolder.bitmapImageView));
//
//            taskWeakReference.get().execute(bitmapModle);
//            new ImageDownloaderAsynTask(activity,viewHolder.bitmapImageView).execute(bitmapModle);
//            viewHolder.bitmapImageView.setImageBitmap(BitmapFactory.decodeResource(activity.getResources(), R.drawable.no));
        viewHolder.bitmapImageView.setTag(new WeakReference<SetImageTask>(
                new SetImageTask(viewHolder.bitmapImageView, cache)));
        tcImageLoader.display(bitmapModle.getUrl(), viewHolder.bitmapImageView, R.drawable.no);
//        }
//        if(bitmapModle.getBitmap()!=null)
//        {
//            viewHolder.bitmapImageView.setImageBitmap(bitmapModle.getBitmap());
//        }
        viewHolder.urlTextView.setText(bitmapModle.getUrl());
        return view;
    }


    public class ViewHolder {
        public TextView urlTextView;
        public ImageView bitmapImageView;

    }
}
