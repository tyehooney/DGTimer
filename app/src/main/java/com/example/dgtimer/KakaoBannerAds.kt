package com.example.dgtimer

import android.content.Context
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.kakao.adfit.ads.AdListener
import com.kakao.adfit.ads.ba.BannerAdView

fun setAd(bannerAdView: BannerAdView, context: Context,
          lifecycle: Lifecycle){

    bannerAdView.setClientId(context.getString(R.string.adfit_id))
    bannerAdView.setAdListener(object : AdListener{
        override fun onAdLoaded() {
        }

        override fun onAdFailed(p0: Int) {
            Log.d("TAGTAG", "ad Failed : "+p0)
        }

        override fun onAdClicked() {
        }
    })

    lifecycle.addObserver(object : LifecycleObserver{

        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        fun onResume(){
            bannerAdView.resume()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        fun onPause(){
            bannerAdView.pause()
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy(){
            bannerAdView.destroy()
        }
    })

    bannerAdView.loadAd()
}