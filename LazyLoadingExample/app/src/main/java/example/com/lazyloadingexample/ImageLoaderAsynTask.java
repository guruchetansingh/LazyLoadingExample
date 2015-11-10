package example.com.lazyloadingexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * Created by guruchetan on 17/8/15.
 */
public class ImageLoaderAsynTask extends AsyncTask<String, String, String> {

    String string;
    private WeakReference<ImageView> imageViewWeakReference;
    private WeakReference<ProgressBar> progressBarWeakReference;

    @Override
    protected void onPreExecute() {

        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    private Bitmap getBitmap() {
        Bitmap bitmapZzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx = null;


        try {
//            bitmap = BitmapFactory.decodeStream((InputStream) new URL("urls").getContent());

        } catch (Exception e) {
            Log.e("LL", e.toString());
            e.printStackTrace();
        }
        return /*bitmap*/null;
    }
}
