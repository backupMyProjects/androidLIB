package com.leo.android.util.widget;

import com.leo.android.util.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class MenuFaceLL extends LinearLayout {
	private LinearLayout menu;
	public boolean menuShowing = false;
	
	private static int menuDuration = 300;
	public static void setMenuDuration(int input){menuDuration = input;}

	public MenuFaceLL(Context context, AttributeSet attrs, int llLayout, int inLayoutID) {
		super(context, attrs);
		try {
			//LayoutInflater.from(getContext()).inflate(R.layout.rbm_menu, this, true);
			LayoutInflater.from(getContext()).inflate(llLayout, this, true);
		} catch (Exception e) {
		}
		//menu = (LinearLayout) findViewById(R.id.menulayout);
		menu = (LinearLayout) findViewById(inLayoutID);
	}

	public void showMenu() {
		menu.setVisibility(View.VISIBLE);
		//menu.startAnimation(AnimationUtils.loadAnimation(getContext(), R.menu.rbm_in_from_left));
		Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.menu_in_from_left);
		anim.setDuration(menuDuration);
		menu.startAnimation(anim);
		menuShowing = true;
	}

	public void hideMenu() {
		menu.setVisibility(View.GONE);
		//menu.startAnimation(AnimationUtils.loadAnimation(getContext(), R.menu.rbm_out_to_left));
		Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.menu_out_to_left);
		anim.setDuration(menuDuration);
		menu.startAnimation(anim);
		menuShowing = false;
	}

	public void toggleMenu() {
		if (menu.getVisibility() == View.GONE) {
			showMenu();
		} else {
			hideMenu();
		}
	}
}