package org.elva.elvaapp;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
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

		CardAdapter<Card> adapter = new CardAdapter<Card>(this)
		    // This sets the color displayed for card titles and header actions by default
		    .setAccentColorRes(R.color.ELVA_BLUE);

		// Add a basic header and three cards below it
		adapter.add(new CardHeader("Header 1"));
		adapter.add(new Card("One", "Example 1"));
		adapter.add(new Card("Two", "Example 2"));
		adapter.add(new Card("Three", "Example 3"));

		// Add a header with a subtitle and action, along with 3 more cards below it
		adapter.add(new CardHeader("Header 2", "Subtitle 2").setAction("Hello", new CardHeader.ActionListener() {
		    @Override
		    public void onClick(CardHeader header) {
		        Toast.makeText(getApplicationContext(), header.getActionTitle(), Toast.LENGTH_SHORT).show();
		    }
		}));
		adapter.add(new Card("Four", "Example 4"));
		adapter.add(new Card("Five", "Example 5"));
		adapter.add(new Card("Six", "Example 6"));

		list.setAdapter(adapter);
		
		list.setOnCardClickListener(new CardListView.CardClickListener() {
		    @Override
		    public void onCardClick(int index, CardBase card, View view) {
		        // Do what you want here
		    }

		});
	}

	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.projects_overview, menu);
		return true;
	}

//	@Override
//	public void onMenuItemClick(Card card, MenuItem item) {
//		// TODO Auto-generated method stub
//		
//	}

}
