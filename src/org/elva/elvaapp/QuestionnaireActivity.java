package org.elva.elvaapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;
import org.elva.elvaapp.cards.*;
import org.elva.elvaapp.model.Data;
import org.elva.elvaapp.model.Project;
import org.elva.elvaapp.model.Question;
import org.elva.elvaapp.model.Questionaire;

public class QuestionnaireActivity extends ActionBarActivity {

	Button GetContent;
	int locationId;
	int projectId;
	int questionaireId;
	CustomCardAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire);
		
		Intent intent = this.getIntent();
		
		locationId = intent.getExtras().getInt("location");
		projectId = intent.getExtras().getInt("project");
		questionaireId = intent.getExtras().getInt("questionaire");
		
		


		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		CardListView list = (CardListView)findViewById(android.R.id.list);
//		list.setItemsCanFocus(true);

		list.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);

		adapter = (CustomCardAdapter) new CustomCardAdapter(this)
		.setAccentColorRes(android.R.color.holo_blue_dark);
		
		Questionaire questionaire = Data.getLocation(locationId).getProject(projectId).getQuestionnaire(questionaireId);
		
		for(int i = 0; i < questionaire.getQuestions().size(); i++) {
			System.out.println(""+i);
			adapter = AddQuestionCard(questionaire.getQuestions().get(i), adapter);
		}

//		// Add a RadioCard
//		adapter.add(new RadioCard("Question?", new String[] {"Antwoord 1","Antwoord 2","Antwoord 3"}));
//		adapter.add(new SeekBarCard("Question", 0, 100, 50));
//		adapter.add(new TextCard("Question"));
		
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
					
					// If it is a TextCard
					if(adapter.getItem(i).getClass().getSimpleName().equals("TextCard") ) {
						EditText et = (EditText)((TextCard)adapter.getItem(i)).getView().findViewById(R.id.textInput);
						String toast = "" + et.getText();
						Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
					}
				}


			}
		});

	}

	public CustomCardAdapter AddQuestionCard(Question q, CustomCardAdapter adapter) {
		CustomCardAdapter result = adapter;
		if(q.getQuestionType() == 0) {
			result.add(new TextCard(q.getQuestion()));
		}
		else if(q.getQuestionType() == 1){
			result.add(new RadioCard(q.getQuestion(), q.getAnswers()));
		}
		else if(q.getQuestionType() == 2){
			result.add(new SeekBarCard(q.getQuestion(), Integer.parseInt(q.getAnswers()[0]), Integer.parseInt(q.getAnswers()[1]), Integer.parseInt(q.getAnswers()[2])));
			
		} else {
			System.out.println("Invalid question!, Question is not added");
		}
		return result;
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
