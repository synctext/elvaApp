package org.elva.elvaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.elva.elvaapp.cards.*;

public class ProjectsOverviewActivity extends ActionBarActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_projects_overview);
		
		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
//		CardListView list = (CardListView)findViewById(android.R.id.list);
//
//		CustomCardAdapter adapter = (CustomCardAdapter) new CustomCardAdapter(this)
//	    .setAccentColorRes(android.R.color.holo_blue_dark);
//		adapter.add(new RadioCard("Question?", new String[] {"Ankit","Bohra","Xyz"}));
//
//
//		list.setAdapter(adapter);
//		
//		list.setOnCardClickListener(new CardListView.CardClickListener() {
//		    @Override
//		    public void onCardClick(int index, CardBase card, View view) {
//		        // Do what you want here
//		    	Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
//		    	intent.putExtra("cardTitle", card.getTitle());
//				startActivity(intent);
//		    }
//
//		});
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
	        this.finish();
	        return true;
	    }
	    return super.onOptionsItemSelected(item);
	}
	
	//@Override
	//public boolean onCreateOptionsMenu(Menu menu) {
	//	// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.projects_overview, menu);
	//	return true;
	//}

//	@Override
//	public void onMenuItemClick(Card card, MenuItem item) {
//		// TODO Auto-generated method stub
//		
//	}

}
