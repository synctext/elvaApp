package org.elva.elvaapp;

import org.elva.elvaapp.cards.*;

import android.graphics.drawable.Drawable;
import android.view.View;

public class SeekBarCard implements CardBase<SeekBarCard> {
    private int mIndex;
    private int mMin;
    private int mMax;
    private int mPosition;
    private String title;
    private View View;

    public SeekBarCard(int index, int min, int max, int position) {
        mIndex = index;
        mMin = min;
        mMax = max;
        mPosition = position;
       
    }
    
    public SeekBarCard(String ntitle, int min, int max, int position) {
        title = ntitle;
        mMin = min;
        mMax = max;
        mPosition = position;
       
    }
    
    public int getMax(){
    	return mMax;
    }
    
    public int getMin(){
    	return mMin;
    }
    
    public int getInitialPosition(){
    	return mPosition;
    }

    public void setView(View v) {
    	View = v;
    }
    
    public View getView() {
    	return View;
    }
    @Override
    public int getLayout() {
        return R.layout.question_scale;
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
    public Card.CardMenuListener<SeekBarCard> getPopupListener() {
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
    public boolean equalTo(SeekBarCard other) {
        return mIndex == other.mIndex;
    }
    

}
