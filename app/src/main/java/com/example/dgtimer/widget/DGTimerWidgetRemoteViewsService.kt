package com.example.dgtimer.widget

import android.content.Intent
import android.util.Log
import android.widget.RemoteViewsService
import com.example.dgtimer.repo.CapsuleRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DGTimerWidgetRemoteViewsService : RemoteViewsService() {

    @Inject
    lateinit var repository: CapsuleRepository

    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        Log.d("DGTimerWidgetRemoteViewsService", "onGetViewFactory()")
        return DGTimerWidgetRemoteViewsFactory(applicationContext, repository)
    }
}