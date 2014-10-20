/**
 * Copyright (C) 2013
 * @Author: Liu Lang
 * Email: developer.lang@gmail.com
 * Version: 1.0.0
 */
package com.framework.image;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageRender {
    private static ImageRender sInstance = new ImageRender();
    private ImageRender() {
    }
    
    public static ImageRender getInstance() {
        return sInstance;
    }
    
    public void init(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheSize(50 * 1024 * 1024) // 50 Mb
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .writeDebugLogs() // Remove for release app
                .build();
        ImageLoader.getInstance().init(config);
    }

    public void clear() {
        ImageLoader.getInstance().clearMemoryCache();
    }

    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID) {
        setImage(imageView, urlStr, defaultImageResourceID, defaultImageResourceID, 0, true);
    }
    
    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID, int cornerRadiusPixels, boolean cache) {
        setImage(imageView, urlStr, defaultImageResourceID, defaultImageResourceID, cornerRadiusPixels, cache);
    }

    public void setImage(ImageView imageView, String urlStr, int defaultImageResourceID, int errorImageResoureID, int cornerRadiusPixels, boolean cache) {
        if (imageView == null) {
            return;
        }
        if (TextUtils.isEmpty(urlStr)) {
            if (defaultImageResourceID > 0) {
                imageView.setImageResource(defaultImageResourceID);
            }
            return;
        }
        DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageOnLoading(defaultImageResourceID)
            .showImageForEmptyUri(defaultImageResourceID)
            .showImageOnFail(errorImageResoureID)
            .cacheInMemory(cache)
            .cacheOnDisk(cache)
            .considerExifParams(true)
            .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
            .build();
        ImageLoader.getInstance().displayImage(urlStr, imageView, options);
    }
}