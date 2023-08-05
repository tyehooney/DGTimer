package com.example.dgtimer.widget

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.RemoteViews
import android.widget.RemoteViewsService.RemoteViewsFactory
import com.bumptech.glide.Glide
import com.example.dgtimer.R
import com.example.dgtimer.db.Capsule
import com.example.dgtimer.repo.CapsuleRepository
import kotlinx.coroutines.runBlocking

class DGTimerWidgetRemoteViewsFactory(
    private val context: Context,
    private val repository: CapsuleRepository
) : RemoteViewsFactory {

    private var starredCapsules = mutableListOf<WidgetCapsuleItem>()

    override fun onCreate() {
        log("onCreate()")
    }

    override fun onDataSetChanged() {
        log("onDataSetChanged()")
        updateStarredCapsuleList()
    }

    override fun onDestroy() {
        log("onDestroy()")
        starredCapsules.clear()
    }

    override fun getCount(): Int {
        log("getCount() count: ${starredCapsules.size}")
        return starredCapsules.size
    }

    override fun getViewAt(position: Int): RemoteViews {
        log("getViewAt() position:$position")
        val capsuleItem = starredCapsules[position]
        val fillInIntent = Intent().apply {
            putExtra(DGTimerWidgetProvider.EXTRA_ITEM_ID, capsuleItem.id)
        }
        val futureTarget = Glide.with(context)
            .asBitmap()
            .load(capsuleItem.image)
            .submit()
        return RemoteViews(context.packageName, R.layout.item_capsule_widget).apply {
            setImageViewBitmap(R.id.iv_capsule, futureTarget.get())
            setInt(R.id.fl_capsule_color, "setBackgroundColor", capsuleItem.color)
            setTextViewText(R.id.tv_capsule_name, capsuleItem.name)
            setOnClickFillInIntent(R.id.ll_capsule_root, fillInIntent)
        }
    }

    private fun updateStarredCapsuleList() {
        runBlocking {
            repository.getStarredCapsules()?.map {
                it.toWidgetCapsuleItem()
            }?.let {
                starredCapsules.clear()
                starredCapsules.addAll(it)
            }
        }
    }

    private fun Capsule.toWidgetCapsuleItem(): WidgetCapsuleItem =
        WidgetCapsuleItem(id, name, colorAsInt, image)

    private fun log(message: String) {
        Log.d("DGTimerWidgetRemoteViewsFactory", message)
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = starredCapsules[position].id.toLong()

    override fun hasStableIds(): Boolean = false
}