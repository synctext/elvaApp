package org.elva.elvaapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

public class ProjectActivity extends ActionBarActivity {

	private Typeface t;
	private TextView projectTitle;
	private TextView projectDescription;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		
		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);
		
		// Map view
				setUpMap();
				
				projectTitle = (TextView)findViewById(R.id.project_title);
				t = Typeface.createFromAsset(this.getAssets(),"fonts/Roboto-Light.ttf");
				projectTitle.setTypeface(t);
				projectTitle.setText(R.string.project_title);
				projectTitle.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
				
				projectDescription = (TextView)findViewById(R.id.project_description);
				projectDescription.setTypeface(t);
				projectDescription.setText(R.string.lorem_ipsum);
	}

	private void setUpMap() {
		// Get a handle to the Map Fragment
        GoogleMap map = ((MapFragment) getFragmentManager()
                .findFragmentById(R.id.map)).getMap();

        LatLng location = new LatLng(53.219396,6.566391);
        map.setMyLocationEnabled(false);
        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setZoomControlsEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 10));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.project, menu);
		return true;
	}

}
