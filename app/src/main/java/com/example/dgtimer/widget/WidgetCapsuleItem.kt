package com.example.dgtimer.widget

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WidgetCapsuleItem(
    val id: Int,
    val name: String,
    val color: Int,
    val image: String?
) : Parcelable