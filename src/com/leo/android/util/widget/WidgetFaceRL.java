package com.leo.android.util.widget;

import static com.leo.android.util.Constants.*;

import com.leo.android.util.CommonFunction;
import com.leo.android.util.R;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;
/** TODO
 * 
 * @author leo
 *
 */
public abstract class WidgetFaceRL extends RelativeLayout {
	protected String TAG = WidgetFaceRL.class.getName();
	public String getTAG(){return TAG;}

	protected RelativeLayout layout;
	protected CommonFunction cf;
	protected Application application;
	protected Activity activity;
	protected Context context;
	
	protected boolean wfVisiable = false;
	protected boolean isNewest = false;
	
	public boolean getWFVisiable(){return wfVisiable;}
	public boolean getIsNewest(){return isNewest;}

	private static int layoutHidingDelay = 300;
	private static int layoutDuration = 500;
	private static int layoutShowingDelay = layoutHidingDelay + layoutDuration;
	
	public static void setLayoutHidingDelay(int input){layoutHidingDelay = input;}
	public static void setLayoutDuration(int input){layoutDuration = input;}
	public static int getLayoutHidingDelay(){return layoutHidingDelay;}
	public static int getLayoutDuration(){return layoutDuration;}
	public static int getLayoutShowingDelay(){return layoutShowingDelay;}

	public WidgetFaceRL(Context context, AttributeSet attrs, int defStyle, int relLayout, int inLayoutID) {
		super(context, attrs, defStyle);
		init(context, relLayout, inLayoutID);
		//layout = (RelativeLayout) findViewById(R.id.layout);
	}
	public WidgetFaceRL(Context context, AttributeSet attrs, int relLayout, int inLayoutID) {
		super(context, attrs);
		init(context, relLayout, inLayoutID);
		//layout = (RelativeLayout) findViewById(R.id.layout);
	}
	
	private void init(Context context, int relLayout, int inLayoutID){
		this.context = context;
		this.activity = (Activity)context;
		this.application = activity.getApplication();
		try {
			cf = new CommonFunction(context);
			LayoutInflater.from(getContext()).inflate(relLayout, this, true);
		} catch (Exception e) {
		}
		layout = (RelativeLayout) findViewById(inLayoutID);
		setupComponents();
	}
	
	protected void setupComponents(){}
	
	public void wfShow() {
		layout.setVisibility(View.VISIBLE);
		if ( !wfVisiable ){
			Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.enter_alpha);
			anim.setStartOffset(getLayoutShowingDelay());
			anim.setDuration(getLayoutDuration());
			layout.startAnimation(anim);
		}
		wfVisiable = true;
		
	}

	public void wfHide() {
		layout.setVisibility(View.GONE);
		if ( wfVisiable ){
			Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.exit_alpha);
			anim.setStartOffset(getLayoutHidingDelay());
			anim.setDuration(getLayoutDuration());
			layout.startAnimation(anim);
		}
		wfVisiable = false;
	}
	
	public void reShow(View v){
		// ToDo : make it as animation class
		Animation fadeOut = new AlphaAnimation(1, 0);
		//fadeOut.setStartOffset(400);
		fadeOut.setDuration(400);

		Animation fadeIn = new AlphaAnimation(0, 1);
		fadeIn.setStartOffset(400);
		fadeIn.setDuration(400);

	    AnimationSet animation = new AnimationSet(true);
	    animation.addAnimation(fadeOut);
	    animation.addAnimation(fadeIn);
	    v.setAnimation(animation);
	}

}
