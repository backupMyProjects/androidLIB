package android.util.widget;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.cache.ImageCache;
import android.util.cache.ImageFetcher;


import org.apache.commons.lang.StringUtils;

public abstract class WidgetFaceRLCache extends WidgetFaceRL{

    protected ImageFetcher fetcher;
    private int width = 0 ,height = 0;
    private String file = "";

    public WidgetFaceRLCache(Context context, AttributeSet attrs, int defStyle, int relLayout, int inLayoutID) {
        super(context, attrs, defStyle, relLayout, inLayoutID);
    }

    public WidgetFaceRLCache(Context context, AttributeSet attrs, int relLayout, int inLayoutID) {
        super(context, attrs, relLayout, inLayoutID);
    }

    public WidgetFaceRLCache(Context context,int relLayout, int inLayoutID) {
        super(context, relLayout, inLayoutID);
    }

    @Override
    protected void setupComponents(){
        checkSizeAndFile();
        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(activity, file);
        cacheParams.setMemCacheSizePercent(0.25f);
        fetcher = new ImageFetcher(activity,width ,height);
        fetcher.addImageCache(((FragmentActivity) activity).getSupportFragmentManager(), cacheParams);
        setupComponentCache();
    }

    protected abstract void setupComponentCache();

    protected abstract int itemWidth();

    protected abstract int itemHeight();

    protected abstract String cacheFileName();

    private void checkSizeAndFile(){
        if(itemWidth() == 0){
            width = 50;
        }else {
            width = itemWidth();
        }
        if(itemHeight() == 0){
            height = 50;
        }else {
            height = itemHeight();
        }
        if(StringUtils.isBlank(cacheFileName())){
            file = "/imageCache/";
        }else{
            file = cacheFileName();
        }
    }
}
