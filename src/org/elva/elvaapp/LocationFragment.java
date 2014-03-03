package org.elva.elvaapp;


import org.elva.elvaapp.model.Data;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LocationFragment extends ListFragment {

	String[] numbers_text = new String[] { "one", "two", "three", "four",
			"five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve",
			"thirteen", "fourteen", "fifteen" };

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				inflater.getContext(), android.R.layout.simple_list_item_1,
				Data.getLocationNames());
		setListAdapter(adapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

}