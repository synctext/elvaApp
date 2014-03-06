package org.elva.elvaapp;

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

public class ProjectsFragment extends ListFragment {

	private int location;

	
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(getActivity().getApplicationContext(), ProjectActivity.class);
		intent.putExtra("project", position);
		intent.putExtra("location", location);
		startActivity(intent);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		// Get location
		SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
		location = sharedPref.getInt("location", 0);

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, Data
				.getLocation(location).getProjectNames());
		setListAdapter(adapter);

		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
}