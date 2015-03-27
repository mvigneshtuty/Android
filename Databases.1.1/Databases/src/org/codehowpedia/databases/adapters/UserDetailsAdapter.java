package org.codehowpedia.databases.adapters;

import java.util.ArrayList;

import org.codehowpedia.databases.R;
import org.codehowpedia.databases.domain.UserDetails;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UserDetailsAdapter extends ArrayAdapter<UserDetails> {
	private Context activityCtx;
private ArrayList<UserDetails> userDetailsData; 
	public UserDetailsAdapter(Context context, int resource, ArrayList<UserDetails> objects) {
		super(context, resource, objects);
		this.activityCtx =  context;
		this.userDetailsData =  objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = ((Activity) activityCtx).getLayoutInflater();
		View rowView = inflater.inflate(R.layout.contents_displaydetails_listview, null);
		ImageView userImageView = (ImageView) rowView
				.findViewById(R.id.user_img);
		if (userDetailsData.get(position).getUserImage() == null) {
			userImageView.setImageResource(R.drawable.ic_launcher);
		}

		TextView userIdView = (TextView) rowView
				.findViewById(R.id.user_id);
		userIdView.setText(userDetailsData.get(position).getId());

		TextView userNameView = (TextView) rowView
				.findViewById(R.id.user_name);
		userNameView.setText(userDetailsData.get(position).getUserName());

		TextView emailIdView = (TextView) rowView
				.findViewById(R.id.email_id);
		emailIdView.setText(userDetailsData.get(position).getEmailId());
		
		return rowView;
	}

}
