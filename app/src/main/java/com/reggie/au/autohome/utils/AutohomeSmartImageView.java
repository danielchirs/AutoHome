package com.reggie.au.autohome.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;

import com.loopj.android.image.SmartImageView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by michaelchen on 2015/3/30.
 */
public class AutohomeSmartImageView extends SmartImageView {
    private String myImageUrl;
    private Timer imageRefreshTimer;

    boolean useImageCache = true;

    public AutohomeSmartImageView(Context context) {
        super(context);
    }

    public AutohomeSmartImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AutohomeSmartImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImageUrl(String url) {
        this.myImageUrl = url;
        setImage(new AutohomeWebImage(url));
    }

    public void setImageUrl(String url, final Integer fallbackResource) {
        this.myImageUrl = url;
        setImage(new AutohomeWebImage(url), fallbackResource);
    }

    public void setImageUrl(String url, final Integer fallbackResource, final Integer loadingResource) {
        this.myImageUrl = url;
        setImage(new AutohomeWebImage(url), fallbackResource, loadingResource);
    }

    public void setImageUrl(String url, boolean useImageCache) {
        this.myImageUrl = url;
        this.useImageCache = useImageCache;
        setImage(new AutohomeWebImage(url, useImageCache));
    }

    public void setRefreshRate(int msec) {
        Log.i("AutohomeSmartImageView", "Setting image refresh rate to " + msec + " msec");
        if (this.imageRefreshTimer != null)
            this.imageRefreshTimer.cancel();
        this.imageRefreshTimer = new Timer();
        final Handler timerHandler = new Handler() {
            public void handleMessage(Message msg) {
                Log.i("MySmartImageView", "Refreshing image at " + AutohomeSmartImageView.this.myImageUrl);
                AutohomeSmartImageView.this.setImage(new AutohomeWebImage(AutohomeSmartImageView.this.myImageUrl, false));
            }
        };
        imageRefreshTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timerHandler.sendEmptyMessage(0);
            }
        }, msec, msec);
    }

    public void cancelRefresh() {
        if (this.imageRefreshTimer != null)
            this.imageRefreshTimer.cancel();
    }
}
