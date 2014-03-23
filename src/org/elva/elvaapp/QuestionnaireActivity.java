package org.elva.elvaapp;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
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
	int lastPressedId;
	CustomCardAdapter adapter;

	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 200;

	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	private Activity thisActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questionnaire);

		thisActivity = this;
		Intent intent = this.getIntent();

		locationId = intent.getExtras().getInt("location");
		projectId = intent.getExtras().getInt("project");
		questionaireId = intent.getExtras().getInt("questionaire");

		// Setup actionbar
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeButtonEnabled(true);

		CardListView list = (CardListView)findViewById(android.R.id.list);

		list.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
		list.setTranscriptMode(ListView.TRANSCRIPT_MODE_NORMAL);
		list.setStackFromBottom(true);

		adapter = (CustomCardAdapter) new CustomCardAdapter(this)
		.setAccentColorRes(android.R.color.holo_blue_dark);

		Questionaire questionaire = Data.getLocation(locationId).getProject(projectId).getQuestionnaire(questionaireId);

		for(int i = 0; i < questionaire.getQuestions().size(); i++) {
			System.out.println(""+i);
			adapter = AddQuestionCard(questionaire.getQuestions().get(i), adapter);
		}

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
					else if(adapter.getItem(i).getClass().getSimpleName().equals("SeekBarCard") ){
						SeekBar sb =(SeekBar)((SeekBarCard)adapter.getItem(i)).getView().findViewById(R.id.seekbar);
						int progress = sb.getProgress();
						String toast = "" + progress;
						Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
					}

					// If it is a TextCard
					else if(adapter.getItem(i).getClass().getSimpleName().equals("TextCard") ) {
						EditText et = (EditText)((TextCard)adapter.getItem(i)).getView().findViewById(R.id.textInput);
						String toast = "" + et.getText();
						Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_LONG).show();
					}

					// If it is a PhotoCard
					else if(adapter.getItem(i).getClass().getSimpleName().equals("PhotoCard") ){
						Uri u = ((PhotoCard)adapter.getItem(i)).getImagePath();
						Toast.makeText(getApplicationContext(), "Image Location is:\n" +
								u, Toast.LENGTH_LONG).show();

					}
				}


			}
		});
		setupUI(this.findViewById(R.id.parent));

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
		}
		else if(q.getQuestionType() == 3){
			result.add(new PhotoCard(q.getQuestion(), q.getAnswers()));		
		} else {
			System.out.println("Invalid question!, Question is not added");
		}
		return result;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(data!=null){
			if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE || requestCode == GALLERY_IMAGE_ACTIVITY_REQUEST_CODE) {
				if (resultCode == RESULT_OK) {
					// Image captured and saved to fileUri specified in the Intent
					//	            Toast.makeText(this, "Image saved to:\n" +
					//	                     data.getData(), Toast.LENGTH_LONG).show();
					for(int i = 0; i < adapter.getCount(); i++){
						if(adapter.getItem(i).getClass().getSimpleName().equals("PhotoCard") ){
							System.out.println("Wants photo:" +  ((PhotoCard)adapter.getItem(i)).wantsPhoto());
							if(((PhotoCard)adapter.getItem(i)).wantsPhoto()){
								ImageView iv =(ImageView)((PhotoCard)adapter.getItem(i)).getView().findViewById(R.id.imagePreview);
								iv.setImageURI(data.getData());
								((PhotoCard)adapter.getItem(i)).setImagePath(data.getData());
								((PhotoCard)adapter.getItem(i)).setWantsPhoto(false);
							}
						}
					}

				} else if (resultCode == RESULT_CANCELED) {
					// User cancelled the image capture
					for(int i = 0; i < adapter.getCount(); i++){
						if(adapter.getItem(i).getClass().getSimpleName().equals("PhotoCard") ){
							if(((PhotoCard)adapter.getItem(i)).wantsPhoto()){
								((PhotoCard)adapter.getItem(i)).setWantsPhoto(false);
							}
						}
					}
				} else {
					// Image capture failed, advise user
					for(int i = 0; i < adapter.getCount(); i++){
						if(adapter.getItem(i).getClass().getSimpleName().equals("PhotoCard") ){
							if(((PhotoCard)adapter.getItem(i)).wantsPhoto()){
								((PhotoCard)adapter.getItem(i)).setWantsPhoto(false);
							}
						}
					}
				}
			}
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		//        outState.putString("YourTextViewTextIdentifier", yourTextView.getText().toString());
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		System.out.println("Restore InstanceSTATE!");


	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.projects_overview, menu);
		return true;
	}
	
	public void setupUI(View view) {

	    //Set up touch listener for non-text box views to hide keyboard.
	    if(!(view instanceof EditText)) {

	        view.setOnTouchListener(new OnTouchListener() {

	            public boolean onTouch(View v, MotionEvent event) {
	                hideSoftKeyboard(thisActivity);
	                return false;
	            }

	        });
	    }

	    //If a layout container, iterate over children and seed recursion.
	    if (view instanceof ViewGroup) {

	        for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

	            View innerView = ((ViewGroup) view).getChildAt(i);

	            setupUI(innerView);
	        }
	    }
	}
	
	public static void hideSoftKeyboard(Activity activity) {
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	}


	//	@Override
	//	public void onMenuItemClick(Card card, MenuItem item) {
	//		// TODO Auto-generated method stub
	//		
	//	}

}
