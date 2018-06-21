package com.pappayaed.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.internal.BaselineLayout;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.widget.BottomNavigationView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pappayaed.R;

import java.lang.reflect.Field;

/**
 * Created by yasar on 28/5/18.
 */

public class ExtendedBottomNavigationView extends BottomNavigationView {
    private final Context context;
    private Typeface fontFace = null;

    public ExtendedBottomNavigationView(Context context, AttributeSet attrs){
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom){
        super.onLayout(changed, left, top, right, bottom);
        final ViewGroup bottomMenu = (ViewGroup)getChildAt(0);
        final int bottomMenuChildCount = bottomMenu.getChildCount();
        BottomNavigationItemView item;
        View itemTitle;
        Field shiftingMode;

        if(fontFace == null){
//            fontFace = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.VazirBold));
        }
        try {
            //if you want to disable shiftingMode:
            //shiftingMode is a private member variable so you have to get access to it like this:
            shiftingMode = bottomMenu.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(bottomMenu, false);
            shiftingMode.setAccessible(false);
        } catch (NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e){e.printStackTrace();}

        for(int i=0; i<bottomMenuChildCount; i++){
            item = (BottomNavigationItemView)bottomMenu.getChildAt(i);
            //this shows all titles of items
            item.setChecked(true);
            //every BottomNavigationItemView has two children, first is an itemIcon and second is an itemTitle
            itemTitle = item.getChildAt(1);
            //every itemTitle has two children, first is a smallLabel and second is a largeLabel. these two are type of AppCompatTextView
            ((TextView)((BaselineLayout) itemTitle).getChildAt(0)).setTypeface(fontFace, Typeface.NORMAL);
            ((TextView)((BaselineLayout) itemTitle).getChildAt(1)).setTypeface(fontFace, Typeface.NORMAL);
        }
    }
}