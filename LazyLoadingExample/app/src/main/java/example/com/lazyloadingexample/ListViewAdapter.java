package example.com.lazyloadingexample;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by guruchetan on 17/8/15.
 */
public class ListViewAdapter extends BaseAdapter {

    private Activity activity;
    private ArrayList<String> urlsList;
    LayoutInflater layoutInflater;

    public ListViewAdapter(Activity activity, ArrayList<String> urlsList) {
        this.activity = activity;
        this.urlsList = urlsList;
        this.layoutInflater = activity.getLayoutInflater();
    }

    @Override
    public int getCount() {
        return urlsList.size();
    }

    @Override
    public Object getItem(int i) {
        return urlsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int index, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.row_for_images, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_view);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.text_view);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setBackgroundResource(R.mipmap.ic_launcher);
        viewHolder.textView.setText(urlsList.get(index));
        return convertView;
    }

    public class ViewHolder {
        public ImageView imageView;
        public TextView textView;

    }

}
