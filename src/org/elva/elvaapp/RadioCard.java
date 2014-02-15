package org.elva.elvaapp;

import org.elva.elvaapp.cards.*;

import android.graphics.drawable.Drawable;
import android.view.View;

public class RadioCard implements CardBase<RadioCard> {
    private int mIndex;
    private String [] mOptions;
    private String title;
    private View View;

    public RadioCard(int index, String[] options) {
        mIndex = index;
        mOptions = options;
       
    }
    
    public RadioCard(String ntitle, String[] options) {
        title = ntitle;
        mOptions = options;
       
    }
    
    public String [] getOptions() {
    	return mOptions;
    }

    public void setView(View v) {
    	View = v;
    }
    
    public View getView() {
    	return View;
    }
    @Override
    public int getLayout() {
        return R.layout.question_radio_group;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getContent() {
        return "Example " + mIndex;
    }

    @Override
    public boolean isHeader() {
        return false;
    }

    @Override
    public boolean isClickable() {
        // The card will not respond to being tapped
        return false;
    }

    @Override
    public int getPopupMenu() {
        // -1 indicates that the popup menu is disabled for this card
        return -1;
    }

    @Override
    public CardHeader.ActionListener getActionCallback() {
        return null;
    }

    @Override
    public String getActionTitle() {
        return null;
    }

    @Override
    public Card.CardMenuListener<RadioCard> getPopupListener() {
        return null;
    }

    @Override
    public Drawable getThumbnail() {
        return null;
    }

    @Override
    public Object getTag() {
        return null;
    }

    @Override
    public Object getSilkId() {
        return mIndex;
    }

    @Override
    public boolean equalTo(RadioCard other) {
        return mIndex == other.mIndex;
    }
    

}
