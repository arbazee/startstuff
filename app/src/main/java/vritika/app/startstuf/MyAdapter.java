package vritika.app.startstuf;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends ArrayAdapter<Model> {

    private final List<Model> list;
    private final Activity context;
    boolean checkAll_flag = false;
    boolean checkItem_flag = false;

    public MyAdapter(Activity context, List<Model> list) {
        super(context, R.layout.alluser_row, list);
        this.context = context;
        this.list = list;
    }

    static class ViewHolder {
        protected TextView text,phone;
        protected CheckBox checkbox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflator = context.getLayoutInflater();
            convertView = inflator.inflate(R.layout.alluser_row, null);
            viewHolder = new ViewHolder();
            viewHolder.text = (TextView) convertView.findViewById(R.id.tvname);
            viewHolder.checkbox = (CheckBox) convertView.findViewById(R.id.icheck);
            viewHolder.phone=(TextView)convertView.findViewById(R.id.tvphone);
            viewHolder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    int getPosition = (Integer) buttonView.getTag();  // Here we get the position that we have set for the checkbox using setTag.
                    list.get(getPosition).setSelected(buttonView.isChecked()); // Set the value of checkbox to maintain its state.
                }
            });
            convertView.setTag(viewHolder);
            convertView.setTag(R.id.tvname, viewHolder.text);
            convertView.setTag(R.id.tvphone,viewHolder.phone);
            convertView.setTag(R.id.icheck, viewHolder.checkbox);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.checkbox.setTag(position); // This line is important.
        viewHolder.text.setText(list.get(position).getName());
        viewHolder.phone.setText(list.get(position).getphone());
        viewHolder.checkbox.setChecked(list.get(position).isSelected());
         return convertView;
    }
}
