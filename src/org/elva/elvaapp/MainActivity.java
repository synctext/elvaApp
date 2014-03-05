package org.elva.elvaapp;

import org.elva.elvaapp.model.Data;
import org.elva.elvaapp.model.Location;
import org.elva.elvaapp.model.Project;
import org.elva.elvaapp.model.Question;
import org.elva.elvaapp.model.Questionaire;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements LocationFragment.OnHeadlineSelectedListener {
	private String[] mMenuItems;
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;

	// private Typeface t;
	// private TextView projectTitle;
	// private TextView projectDescription;
	// TESTUHH!!??
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		actionBar.setBackgroundDrawable(getResources().getDrawable(R.color.ELVA_WHITE));

		// Setup menuDrawer
		mMenuItems = getResources().getStringArray(R.array.menu_array);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);
		mTitle = mDrawerTitle = getTitle();

		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		// Set the adapter for the list view
		mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, mMenuItems));
		// Set the list's click listener
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getSupportActionBar().setTitle(mTitle);
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getSupportActionBar().setTitle(mDrawerTitle);
				// invalidateOptionsMenu(); // creates call to
				// onPrepareOptionsMenu()
			}
		};
		loadTestData();

		mDrawerLayout.setDrawerListener(mDrawerToggle);
		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	public void loadTestData() {
		Location location = new Location("test location");
		Project project = new Project(location, "test project", "test desciption");
		Questionaire questionaire = new Questionaire(project, "test questionaire");
		Question question = new Question(questionaire, "test question");
		
		Location location2 = new Location("test location2");
		Project project2 = new Project(location2, "test project", "test desciption2");
		Questionaire questionaire2 = new Questionaire(project2, "test questionaire2");
		Question question2 = new Question(questionaire2, "test question2");
		
		new Data();
		Data.addLocation(location);
		Data.addLocation(location2);
	}
	
	   public void onArticleSelected(int position) {
	        // The user selected the headline of an article from the HeadlinesFragment

	        // Capture the article fragment from the activity layout
	        LocationFragment locationFrag = (LocationFragment)
	                getSupportFragmentManager().findFragmentById(R.id.content_frame);

	        if (locationFrag != null) {
	            // If article frag is available, we're in two-pane layout...

	            // Call a method in the ArticleFragment to update its content
				selectItem(1);

	        } else {

	        }
	    }

	public void selectItem(int position) {
		switch (position) {
		case 0: {// select location
			Fragment f = (Fragment) new LocationFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, f);
			ft.commit();
			break;
		}
		case 1: {
			Fragment f = (Fragment) new ProjectsFragment();
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.replace(R.id.content_frame, f);
			ft.commit();
			break;
		}
		}

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private class DrawerItemClickListener implements ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView parent, View view, int position, long id) {
			mDrawerLayout.closeDrawers();

			switch (position) {
			case 0: {
				selectItem(1);
				break;
			}
			case 1: {
				selectItem(0);
				break;
			}
			case 2: {
				Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
				startActivity(intent);
				break;
			}
			case 3: {
				Intent intent = new Intent(getApplicationContext(), ProjectsOverviewActivity.class);
				startActivity(intent);
				break;
			}
			case 4: {
				Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
				startActivity(intent);
				break;
			}
			}

		}
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
