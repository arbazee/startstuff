package vritika.app.startstuf;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by akshay on 01-04-2017.
 */

public class LinkBaseAdapter extends BaseAdapter {
    Context context;

    public LinkBaseAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
