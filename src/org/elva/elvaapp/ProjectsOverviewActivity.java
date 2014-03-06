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
		
		CardListView list = (CardListView)findViewById(android.R.id.list);

		CustomCardAdapter adapter = (CustomCardAdapter) new CustomCardAdapter(this)
	    .setAccentColorRes(android.R.color.holo_blue_dark);
		adapter.add(new RadioCard("Question?", new String[] {"Ankit","Bohra","Xyz"}));
		

		

//		
//		CustomCardAdapter adapter = new CustomCardAdapter(this)
//		    // This sets the color displayed for card titles and header actions by default
//		    .setAccentColorRes(R.color.ELVA_BLUE);

		
//		// Add a basic header and three cards below it
//		adapter.add(new CardHeader("Open projects"));
//		adapter.add(new Card(this, "Test", R.layout.question_radio_group));
//		adapter.add(new Card("Project 1", "This project is about garbage in the streets of Groningen"));
//		adapter.add(new Card("Project 2", "Example 2"));
//		adapter.add(new Card("Project 3", "Example 3"));
//		
//		Card test = adapter.getItem(1);
//		test.setLayout(R.layout.question_radio_group);
//
//		// Add a header with a subtitle and action, along with 3 more cards below it
//		adapter.add(new CardHeader("Private projects").setAction("Locked", new CardHeader.ActionListener() {
//		    @Override
//		    public void onClick(CardHeader header) {
//		        Toast.makeText(getApplicationContext(), header.getActionTitle(), Toast.LENGTH_SHORT).show();
//		    }
//		}));
//		adapter.add(new Card("Four", "Example 4"));
//		adapter.add(new Card("Five", "Example 5"));
//		adapter.add(new Card("Six", "Example 6"));

		list.setAdapter(adapter);
		
		list.setOnCardClickListener(new CardListView.CardClickListener() {
		    @Override
		    public void onCardClick(int index, CardBase card, View view) {
		        // Do what you want here
		    	Intent intent = new Intent(getApplicationContext(), ProjectActivity.class);
		    	intent.putExtra("cardTitle", card.getTitle());
				startActivity(intent);
		    }

		});
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
