package vritika.app.startstuf;

/**
 * Created by akshay on 28-12-2016.
 */

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class CustomListViewAdapter extends ArrayAdapter<RowItem> {

    Context context;
    Boolean flag=false;
    public CustomListViewAdapter(Context context, int resourceId,
                                 List<RowItem> items) {
        super(context, resourceId, items);
        this.context = context;
    }

    /*private view holder class*/
    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        RowItem rowItem = getItem(position);
        String txt=rowItem.getTitle();
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            if(txt.equals(""))
            {
                convertView = mInflater.inflate(R.layout.usergr, null);
                flag=true;
            }
            else {
                convertView = mInflater.inflate(R.layout.alluser_row2, null);
                holder = new ViewHolder();
                holder.txtDesc = (TextView) convertView.findViewById(R.id.tvphone);
                holder.txtTitle = (TextView) convertView.findViewById(R.id.tvname);
                holder.imageView = (ImageView) convertView.findViewById(R.id.gricon);
                convertView.setTag(holder);
                flag=false;
            }
        } else
            holder = (ViewHolder) convertView.getTag();

        if(flag==false) {

            holder.txtDesc.setText(rowItem.getDesc());
            holder.txtTitle.setText(rowItem.getTitle());
            holder.imageView.setImageResource(rowItem.getImageId());
        }

        return convertView;
    }
}
