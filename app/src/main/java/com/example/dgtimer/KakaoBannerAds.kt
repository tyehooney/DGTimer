package com.example.dgtimer

import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.kakao.adfit.ads.AdListener
import com.kakao.adfit.ads.ba.BannerAdView

private const val TAG = "KakaoBannerAds"
fun setAd(bannerAdView: BannerAdView, context: Context, lifecycle: Lifecycle){

    bannerAdView.setClientId(context.getString(R.string.adfit_id))
    bannerAdView.setAdListener(object : AdListener{
        override fun onAdLoaded() {}

        override fun onAdFailed(errorCode: Int) {
            Log.d(TAG, "ad Failed : $errorCode")
        }

        override fun onAdClicked() {}
    })

    lifecycle.addObserver(object : LifecycleEventObserver {
        override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
            when (event) {
                Lifecycle.Event.ON_RESUME -> bannerAdView.resume()
                Lifecycle.Event.ON_PAUSE -> bannerAdView.pause()
                Lifecycle.Event.ON_DESTROY -> bannerAdView.destroy()
                else -> {}
            }
        }
    })

    bannerAdView.loadAd()
}