package org.elva.elvaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import org.elva.elvaapp.cards.*;

public class QuestionnaireActivity extends ActionBarActivity {

	Button GetContent;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire);

		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		CardListView list = (CardListView)findViewById(android.R.id.list);

		final CustomCardAdapter adapter = (CustomCardAdapter) new CustomCardAdapter(this)
		.setAccentColorRes(android.R.color.holo_blue_dark);

		// Add a RadioCard
		adapter.add(new RadioCard("Question?", new String[] {"Antwoord 1","Antwoord 2","Antwoord 3"}));
		adapter.add(new SeekBarCard("Question", 0, 100, 50));
		adapter.add(new TextCard("Question"));


		list.setAdapter(adapter);
				
		// get result on buttonclick
		GetContent = (Button)this.findViewById(R.id.button1);
		GetContent.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// Get a the input of the card
				for(int i = 0; i < adapter.getCount(); i++){
					
					// If it is a RadioCard
					if(adapter.getItem(i).getClass().getSimpleName().equals("RadioCard") ){
						RadioGroup rg =(RadioGroup)((RadioCard)adapter.getItem(i)).getView().findViewById(R.id.radioGroup);
						int selected = rg.getCheckedRadioButtonId();
						String toast = "Nothing selected!";
						if(selected != -1){
							RadioButton rb = (RadioButton)rg.findViewById(selected);
							toast = rb.getText().toString();
						}
						Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT).show();
					}
					// If it is a SeekBarCard
					if(adapter.getItem(i).getClass().getSimpleName().equals("SeekBarCard") ){
						SeekBar sb =(SeekBar)((SeekBarCard)adapter.getItem(i)).getView().findViewById(R.id.seekbar);
						int progress = sb.getProgress();
						String toast = "" + progress;
						Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
					}
				}


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
