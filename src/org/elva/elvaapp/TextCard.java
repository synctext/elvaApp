package org.elva.elvaapp;

import org.elva.elvaapp.cards.*;

import android.graphics.drawable.Drawable;
import android.view.View;

public class TextCard implements CardBase<TextCard> {
    private int mIndex;
    private String title;
    private View View;

    public TextCard(int index) {
        mIndex = index;
       
    }
    
    public TextCard(String ntitle) {
        title = ntitle;
       
    }

    public void setView(View v) {
    	View = v;
    }
    
    public View getView() {
    	return View;
    }
    @Override
    public int getLayout() {
        return R.layout.question_text;
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
        return true;
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
    public Card.CardMenuListener<TextCard> getPopupListener() {
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
    public boolean equalTo(TextCard other) {
        return mIndex == other.mIndex;
    }
    

}

