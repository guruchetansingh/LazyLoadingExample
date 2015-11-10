package com.example.guruchetansingh.lazyloading.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.guruchetansingh.lazyloading.R;
import com.example.guruchetansingh.lazyloading.adaptors.BitmapsAdaptor;
import com.example.guruchetansingh.lazyloading.model.BitmapModle;
import com.example.guruchetansingh.lazyloading.util.Constants;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements Constants {

    private ArrayList<BitmapModle> list = new ArrayList<BitmapModle>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, imageThumbUrls.length+"");
        for (int i = 0; i<imageThumbUrls.length;i++) {
//            Log.d(TAG, "i = "+i+"  "+imageThumbUrls[i]);
            list.add(i, new BitmapModle(null, imageThumbUrls[i]));
        }

        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(new BitmapsAdaptor(this, list));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
