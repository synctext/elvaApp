package org.elva.elvaapp;

import java.util.ArrayList;
import java.util.List;

import org.elva.elvaapp.model.Data;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ProjectsFragment extends ListFragment {

	private int location;

	public enum RowType {
		LIST_ITEM, HEADER_ITEM
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Intent intent = new Intent(getActivity().getApplicationContext(), ProjectActivity.class);
		if (position > 0) {
			intent.putExtra("project", position - 1);
			intent.putExtra("location", location);
			startActivity(intent);
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		// Get location
		SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		location = sharedPref.getInt("location", 0);

		// Data.getLocation(location).getProjectNames()
		//
		//
		// ArrayAdapter<String> adapter = new
		// ArrayAdapter<String>(inflater.getContext(),
		// android.R.layout.simple_list_item_1, );
		// setListAdapter(adapter);
		//
		// return super.onCreateView(inflater, container, savedInstanceState);

		List<Item> items = new ArrayList<Item>();

		ArrayList<String> publicProjects = Data.getLocation(location).getPublicProjectNames();
		ArrayList<String> privateProjects = Data.getLocation(location).getPrivateProjectNames();

		for (int i = 0; i < publicProjects.size(); i++) {
			if (i == 0) {
				items.add(new Header("Public projects"));
			}
			items.add(new ListItem(publicProjects.get(i), ""));
		}

		for (int i = 0; i < privateProjects.size(); i++) {
			if (i == 0) {
				items.add(new Header("Private projects"));
			}
			items.add(new ListItem(privateProjects.get(i), ""));
		}

		TwoTextArrayAdapter adapter = new TwoTextArrayAdapter(inflater.getContext(), items);
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	public interface Item {
		public int getViewType();

		public View getView(LayoutInflater inflater, View convertView);
	}

	public class TwoTextArrayAdapter extends ArrayAdapter<Item> {
		private LayoutInflater mInflater;

		public TwoTextArrayAdapter(Context context, List<Item> items) {
			super(context, 0, items);
			mInflater = LayoutInflater.from(context);
		}

		@Override
		public int getViewTypeCount() {
			return RowType.values().length;

		}

		@Override
		public int getItemViewType(int position) {
			return getItem(position).getViewType();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return getItem(position).getView(mInflater, convertView);
		}
	}

	public class Header implements Item {
		private final String name;

		public Header(String name) {
			this.name = name;
		}

		@Override
		public int getViewType() {
			return RowType.HEADER_ITEM.ordinal();
		}

		@Override
		public View getView(LayoutInflater inflater, View convertView) {
			View view;
			if (convertView == null) {
				view = (View) inflater.inflate(R.layout.header, null);
				// Do some initialization
			} else {
				view = convertView;
			}
			// view.setBackground(getResources().getDrawable((R.drawable.listselector)));

			TextView text = (TextView) view.findViewById(R.id.separator);
			text.setText(name);

			return view;
		}

	}

	public class ListItem implements Item {
		private final String str1;
		private final String str2;

		public ListItem(String text1, String text2) {
			this.str1 = text1;
			this.str2 = text2;
		}

		@Override
		public int getViewType() {
			return RowType.LIST_ITEM.ordinal();
		}

		@Override
		public View getView(LayoutInflater inflater, View convertView) {
			View view;
			if (convertView == null) {
				view = (View) inflater.inflate(R.layout.my_list_item, null);
				// Do some initialization
			} else {
				view = convertView;
			}

			TextView text1 = (TextView) view.findViewById(R.id.list_content1);
			TextView text2 = (TextView) view.findViewById(R.id.list_content2);
			text1.setText(str1);
			text2.setText(str2);

			return view;
		}

	}

}