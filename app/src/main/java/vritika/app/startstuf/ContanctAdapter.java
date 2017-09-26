package vritika.app.startstuf;

import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;


public class ContanctAdapter extends ArrayAdapter<ContactBean> {

	private Activity activity;
	private List<ContactBean> items;
	private List<sendbean> sendarray;
	private int row;
	private ContactBean objBean;
	Boolean check;

	public ContanctAdapter(Activity act, int row, List<ContactBean> items, Boolean check) {
		super(act, row, items);

		this.activity = act;
		this.row = row;
		this.items = items;
		this.check=check;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		if (view == null) {
			LayoutInflater inflater = (LayoutInflater) activity
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflater.inflate(row, null);

			holder = new ViewHolder();
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}

		if ((items == null) || ((position + 1) > items.size()))
			return view;

		objBean = items.get(position);

		holder.tvname = (TextView) view.findViewById(R.id.tvname);
		holder.tvPhoneNo = (TextView) view.findViewById(R.id.tvphone);
		holder.c = (CheckBox) view.findViewById(R.id.icheck);

		try {


			if (check == false) {

				holder.c.setVisibility(view.GONE);
			}



		}
		catch (Exception e) {
			Log.d("Exception is", e.toString());
		}






		if (holder.tvname != null && null != objBean.getName()
				&& objBean.getName().trim().length() > 0) {
			holder.tvname.setText(Html.fromHtml(objBean.getName()));
		}
		if (holder.tvPhoneNo != null && null != objBean.getPhoneNo()
				&& objBean.getPhoneNo().trim().length() > 0) {
			holder.tvPhoneNo.setText(Html.fromHtml(objBean.getPhoneNo()));
		}




		return view;
	}

	public class ViewHolder {
		public TextView tvname, tvPhoneNo;
		public CheckBox c;
	}

}
