package android.util.widget;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.cache.ImageCache;
import android.util.cache.ImageFetcher;


import org.apache.commons.lang.StringUtils;

public abstract class WidgetFragmentCache extends Fragment{

    protected ImageFetcher fetcher;
    protected Activity activity;
    private int width = 0 ,height = 0;
    private String file = "";

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkSizeAndFile();
        ImageCache.ImageCacheParams cacheParams = new ImageCache.ImageCacheParams(activity, file);
        cacheParams.setMemCacheSizePercent(0.25f);
        fetcher = new ImageFetcher(activity,width ,height);
        fetcher.addImageCache(((FragmentActivity) activity).getSupportFragmentManager(), cacheParams);
    }

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
