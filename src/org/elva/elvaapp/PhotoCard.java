package org.elva.elvaapp;

import org.elva.elvaapp.cards.*;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;

public class PhotoCard implements CardBase<PhotoCard> {
    private int mIndex;
    private String [] mOptions;
    private String title;
    private View View;
    private ImageView image;
    private Uri imageLocation;
    private Boolean wantsPhoto;

    public PhotoCard(int index, String[] options) {
        mIndex = index;
        mOptions = options;
        wantsPhoto = false;
       
    }
    
    public PhotoCard(String ntitle, String[] options) {
        title = ntitle;
        mOptions = options;
        wantsPhoto = false;

       
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
    
    public void setImagePath(Uri u){
    	imageLocation = u;
    }
    
    public Uri getImagePath(){
    	return imageLocation;
    }
    
    public void setWantsPhoto(Boolean bool){
    	wantsPhoto = bool;
    }
    
    public Boolean wantsPhoto(){
    	return wantsPhoto;
    }
    @Override
    public int getLayout() {
        return R.layout.question_camera;
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
    public Card.CardMenuListener<PhotoCard> getPopupListener() {
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
    public boolean equalTo(PhotoCard other) {
        return mIndex == other.mIndex;
    }
    

}
