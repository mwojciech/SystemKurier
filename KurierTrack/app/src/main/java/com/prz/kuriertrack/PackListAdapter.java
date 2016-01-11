package com.prz.kuriertrack;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.prz.kuriertrack.Model.Pack;

import java.util.List;

public class PackListAdapter extends ArrayAdapter<Pack> {
	private Activity context;
	private List<Pack> list;

    public PackListAdapter(Activity context, List<Pack> list) {
		super(context, R.layout.pack_list, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
        public TextView tvName;
        public TextView tvDesc;
        public TextView tvStreet;
        public TextView tvAddress;
        public TextView tvCity;
        public TextView tvCountry;
    }

    @Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;


        View rowView = convertView;
		if(rowView == null) {
			LayoutInflater layoutInflater = context.getLayoutInflater();
			rowView = layoutInflater.inflate(R.layout.pack_list, null, true);


			viewHolder = new ViewHolder();
			viewHolder.tvName = (TextView) rowView.findViewById(R.id.tvName);
            viewHolder.tvDesc = (TextView) rowView.findViewById(R.id.tvDesc);
            viewHolder.tvStreet = (TextView) rowView.findViewById(R.id.tvStreet);
            viewHolder.tvAddress = (TextView) rowView.findViewById(R.id.tvAddress);
            viewHolder.tvCity = (TextView) rowView.findViewById(R.id.tvCity);
            viewHolder.tvCountry = (TextView) rowView.findViewById(R.id.tvCountry);

            rowView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) rowView.getTag();
		}


		Pack pack = list.get(position);
		viewHolder.tvName.setText(pack.getName());
        viewHolder.tvDesc.setText(pack.getDescription());
        viewHolder.tvStreet.setText(pack.getStreet());
        viewHolder.tvAddress.setText(pack.getAddress());
        viewHolder.tvCity.setText(pack.getCity());
        viewHolder.tvCountry.setText(pack.getCountry());

		return rowView;
	}
}