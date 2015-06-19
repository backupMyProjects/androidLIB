package android.util.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.cache.ImageCache;
import android.util.cache.ImageFetcher;


import org.apache.commons.lang.StringUtils;

public abstract class MenuFaceLLCache extends MenuFaceLL{

    protected Activity activity;
    protected ImageFetcher fetcher;
    private int width = 0 ,height = 0;
    private String file = "";

    public MenuFaceLLCache(Context context, AttributeSet attrs, int llLayout, int inLayoutID) {
        super(context, attrs, llLayout, inLayoutID);
        this.activity = (Activity)context;
        cacheinit();
    }

    private void cacheinit(){
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
