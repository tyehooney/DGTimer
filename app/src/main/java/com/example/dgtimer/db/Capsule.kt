package com.example.dgtimer.db

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "capsules")
data class Capsule(
    @PrimaryKey
    val id: Int = 0,
    val name: String? = null,
    val type: String? = null,
    @TypeConverters(Converter::class)
    val stage: List<Int>? = null,
    val color: String? = null,
    val image: String? = null,
    @ColumnInfo(name="major")
    val isMajor: Boolean = false
) {
    val colorAsInt: Int
        get() = Color.parseColor(color)
}