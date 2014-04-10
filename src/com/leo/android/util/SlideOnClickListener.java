package com.leo.android.util;

import java.util.Date;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;

public class SlideOnClickListener implements OnClickListener, AnimationListener {
	String TAG = SlideOnClickListener.class.getName();

    View menu;
    View mainApp;
    boolean menuOut = false;
    AnimParams animParams = new AnimParams();
    
    double percentageCover = 0.8;
    int duration = 300;
    
    public void setMenuOut(boolean input){menuOut = input;}
    
	public SlideOnClickListener(View menu, View mainApp){
		this.menu = menu;
		this.mainApp = mainApp;
	}
	
	@Override
    public void onClick(View v) {
        Log.d(TAG, "onClick Menu" + new Date());
        toogle();
    }
	
	public void toogle(){
		Animation anim;

        int w = mainApp.getMeasuredWidth();
        int h = mainApp.getMeasuredHeight();
        int left = (int) (mainApp.getMeasuredWidth() * percentageCover);

        if (!menuOut) {
            // anim = AnimationUtils.loadAnimation(context, R.anim.push_right_out_80);
            anim = new TranslateAnimation(0, left, 0, 0);
            menu.setVisibility(View.VISIBLE);
            animParams.init(left, 0, left + w, h);
        } else {
            // anim = AnimationUtils.loadAnimation(context, R.anim.push_left_in_80);
            anim = new TranslateAnimation(0, -left, 0, 0);
            animParams.init(0, 0, w, h);
            Log.d(TAG, "Run close");
        }

        anim.setDuration(duration);
        anim.setAnimationListener(this);
        //Tell the animation to stay as it ended (we are going to set the app.layout first than remove this property)
        anim.setFillAfter(true);

        mainApp.startAnimation(anim);
	}
	
	static class AnimParams {
        int left, right, top, bottom;

        void init(int left, int top, int right, int bottom) {
            this.left = left;
            this.top = top;
            this.right = right;
            this.bottom = bottom;
        }
    }
	
	@Override
    public void onAnimationEnd(Animation animation) {
        Log.d(TAG, "onAnimationEnd");
        //ViewUtils.printView("menu", menu);
        //ViewUtils.printView("mainApp", mainApp);
        menuOut = !menuOut;
        if (!menuOut) {
            menu.setVisibility(View.INVISIBLE);
        }
        layoutApp(menuOut);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
    }

    @Override
    public void onAnimationStart(Animation animation) {
    }
    
    void layoutApp(boolean menuOut) {
        //Log.d(TAG, "layout [" + animParams.left + "," + animParams.top + "," + animParams.right + "," + animParams.bottom + "]");
        mainApp.layout(animParams.left, animParams.top, animParams.right, animParams.bottom);
        //Now that we've set the app.layout property we can clear the animation, flicker avoided :)
        mainApp.clearAnimation();

    }

}
