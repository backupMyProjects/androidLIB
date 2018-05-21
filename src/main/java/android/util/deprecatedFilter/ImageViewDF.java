package android.util.deprecatedFilter;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class ImageViewDF {

	@SuppressLint("NewApi")
	public void setBackground(ImageView imageView, Drawable drawable) {
		try{
			imageView.setBackground(drawable);
		}catch(NoSuchMethodError e){
			imageView.setBackgroundDrawable(drawable);
		}
	}
}
