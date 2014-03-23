package org.elva.elvaapp;

import java.io.File;

import org.elva.elvaapp.cards.*;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.SystemClock;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CustomCardAdapter extends CardAdapter implements
SeekBar.OnSeekBarChangeListener {

	TextView progressView;
	EditText answer;
	private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	private static final int GALLERY_IMAGE_ACTIVITY_REQUEST_CODE = 200;

	public static final int MEDIA_TYPE_IMAGE = 1;
	private Uri fileUri;
	Context cardContext;

	public CustomCardAdapter(Activity context) {
		super(context);

		cardContext = context;

		// Register three custom card layouts...
		// You must register them so that list items that use these layouts can
		// be recycled by the system correctly.
		registerLayout(R.layout.question_radio_group);
		registerLayout(R.layout.question_scale);
		registerLayout(R.layout.question_text);
		registerLayout(R.layout.question_camera);
		// registerLayout(R.layout.custom_card_two);
		// registerLayout(R.layout.custom_card_three);
	}

	@Override
	protected boolean onProcessThumbnail(ImageView icon, CardBase card) {
		// Optional. If a custom layout has a view with the ID @android:id/icon,
		// this is called.
		// RETURN TRUE: the card thumbnail view is visible.
		// RETURN FALSE: the card thumbnail view is gone (appears to not be
		// there at all).
		return super.onProcessThumbnail(icon, card);
	}

	@Override
	protected boolean onProcessTitle(TextView title, CardBase card,
			int accentColor) {
		// Optional. If a custom layout has a view with the ID
		// @android:id/title, this is called.
		return super.onProcessTitle(title, card, accentColor);
	}

	@Override
	protected boolean onProcessContent(TextView content, CardBase card) {
		// Optional. If a custom layout has a view with the ID
		// @android:id/content, this is called.

		return super.onProcessContent(content, card);
	}

	@Override
	protected boolean onProcessMenu(View view, CardBase card) {
		// Optional. If a custom layout has a view with the ID
		// @android:id/button1, this is called.
		// Do not call the super function if you want to override the default
		// behavior. The super function attaches a popup menu.
		return super.onProcessMenu(view, card);
	}

	@Override
	public View onViewCreated(int index, View recycled, CardBase item) {
		// Optional. You can interact with custom views in your layouts here
		System.out.println(item.getClass().getSimpleName());

		// RadioCard
		if (item.getClass().getSimpleName().equals("RadioCard")) {
			String[] options = ((RadioCard) item).getOptions();
			RadioGroup rg = ((RadioGroup) recycled
					.findViewById(R.id.radioGroup));
			((RadioCard) item).setView(recycled);
			if (rg.getChildCount() == 0) {

				for (int i = 0; i < options.length; i++) {
					RadioButton rb = new RadioButton(this.getContext());
					rb.setText(options[i]);
					rg.addView(rb);
				}
			}
		}

		// SeekBarCard
		if (item.getClass().getSimpleName().equals("SeekBarCard")) {
			int min = ((SeekBarCard) item).getMin();
			int max = ((SeekBarCard) item).getMax();
			int progress = ((SeekBarCard) item).getInitialPosition();
			SeekBar sb = ((SeekBar) recycled.findViewById(R.id.seekbar));
			TextView tv = ((TextView) recycled.findViewById(R.id.min));
			tv.setText("" + min);
			tv = ((TextView) recycled.findViewById(R.id.max));
			tv.setText("" + max);
			progressView = ((TextView) recycled.findViewById(R.id.progress));
			progressView.setText("" + progress);
			((SeekBarCard) item).setView(recycled);
			sb.setMax(max);
			sb.setProgress(progress);
			sb.setOnSeekBarChangeListener(this);
		}

		// TextCard
		if (item.getClass().getSimpleName().equals("TextCard")) {
			((TextCard) item).setView(recycled);
			EditText et = ((EditText)recycled.findViewById(R.id.textInput));
			et.setText(((TextCard)item).getAnswer());



		}

		// PhotoCard
		if (item.getClass().getSimpleName().equals("PhotoCard")) {
			ImageView iv = ((ImageView)recycled.findViewById(R.id.imagePreview));
			Button btnCamera = ((Button)recycled.findViewById(R.id.takePicture));
			Button btnGallery = ((Button)recycled.findViewById(R.id.takePictureFromGallery));
			final PhotoCard pCard = ((PhotoCard)item);

			btnGallery.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					//				    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
					//				    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
					pCard.setWantsPhoto(true);
					Intent pickPhoto = new Intent(Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					pickPhoto.setType("image/*");
					((Activity) cardContext).startActivityForResult(pickPhoto , GALLERY_IMAGE_ACTIVITY_REQUEST_CODE);//one can be replaced with any action code



				}
			});

			btnCamera.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					pCard.setWantsPhoto(true);
					Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

					//				    fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE); // create a file to save the image
					//				    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name

					// start the image capture Intent
					((Activity) cardContext).startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
				}
			});
			((PhotoCard)item).setView(recycled);			

		}
		return super.onViewCreated(index, recycled, item);

	}
	

	// Listener for seekbar
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		progressView.setText("" + progress);

	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}


}
