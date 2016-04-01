package com.gennady.golovin.puzzle15;

import android.app.Activity;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

/**
 * Created by Геннадий on 19.03.2016.
 */
public class Ads {

    public static void showBanner(final Activity activity){
        final AdView mAdView = (AdView)activity.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
               // setupContentViewPadding(activity,mAdView.getHeight());
            }
        });

    }

    public void hideAd(final Activity activity) {
        final AdView adLayout = (AdView)activity.findViewById(R.id.adView);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adLayout.setEnabled(false);
                adLayout.setVisibility(View.GONE);
            }
        });
    }

    public void showAd(final Activity activity) {
        final AdView adLayout = (AdView)activity.findViewById(R.id.adView);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adLayout.setEnabled(true);
                adLayout.setVisibility(View.VISIBLE);
                AdRequest adRequest = new AdRequest.Builder().build();
                adLayout.loadAd(adRequest);
            }
        });
    }

    public static void setupContentViewPadding (Activity activity, int padding){
        View view = activity.findViewById(R.id.staticLayout);
        view.setPadding(view.getPaddingLeft(),view.getPaddingTop(),view.getPaddingRight(),padding);
    }
}
