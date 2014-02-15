package org.elva.elvaapp;

import org.elva.elvaapp.cards.*;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CustomCardAdapter extends CardAdapter{
	
	public CustomCardAdapter(Activity context) {
        super(context);

        // Register three custom card layouts...
        // You must register them so that list items that use these layouts can be recycled by the system correctly.
        registerLayout(R.layout.question_radio_group);
//        registerLayout(R.layout.custom_card_two);
//        registerLayout(R.layout.custom_card_three);
    }

    @Override
    protected boolean onProcessThumbnail(ImageView icon, CardBase card) {
        // Optional. If a custom layout has a view with the ID @android:id/icon, this is called.
        // RETURN TRUE: the card thumbnail view is visible.
        // RETURN FALSE: the card thumbnail view is gone (appears to not be there at all).
        return super.onProcessThumbnail(icon, card);
    }

    @Override
    protected boolean onProcessTitle(TextView title, CardBase card, int accentColor) {
        // Optional. If a custom layout has a view with the ID @android:id/title, this is called.
        return super.onProcessTitle(title, card, accentColor);
    }

    @Override
    protected boolean onProcessContent(TextView content, CardBase card) {
        // Optional. If a custom layout has a view with the ID @android:id/content, this is called.

    	return super.onProcessContent(content, card);
    }

    @Override
    protected boolean onProcessMenu(View view, CardBase card) {
        // Optional. If a custom layout has a view with the ID @android:id/button1, this is called.
        // Do not call the super function if you want to override the default behavior. The super function attaches a popup menu.
        return super.onProcessMenu(view, card);
    }

    @Override
    public View onViewCreated(int index, View recycled, CardBase item) {
        // Optional. You can interact with custom views in your layouts here
    	System.out.println(item.getClass().getSimpleName());
    	
    	// RadioCard
    	if(item.getClass().getSimpleName().equals("RadioCard")){
    		String [] options = ((RadioCard)item).getOptions();
			RadioGroup rg = ((RadioGroup)recycled.findViewById(R.id.radioGroup));
			((RadioCard)item).setView(recycled);
    		for(int i = 0; i < options.length; i++){
    			RadioButton rb = new RadioButton(this.getContext());
    			rb.setText(options[i]);
    			rg.addView(rb);
    		}
    	}

        return super.onViewCreated(index, recycled, item);
        
        
    }


}
