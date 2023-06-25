package com.example.dgtimer.db

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dgtimer.R
import com.example.dgtimer.utils.getCapsuleType

@Entity(tableName = "capsules")
data class Capsule(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val typeId: Int = 0,
    val stage: List<Int> = emptyList(),
    val color: String? = null,
    val image: String? = null,
    @ColumnInfo(name = "major")
    val isMajor: Boolean = false
) {
    val colorAsInt: Int
        get() = Color.parseColor(color)

    val typeStringResId: Int
        get() = when(typeId.getCapsuleType()) {
            CapsuleType.COFFEE -> R.string.capsule_type_coffee
            CapsuleType.GREEN_TEA -> R.string.capsule_type_green_tea
            CapsuleType.CHOCOLATE -> R.string.capsule_type_chocolate
        }
}

data class GlobalCapsule(
    val id: Int = 0,
    val name: String = "",
    val nameInfo: NameInfo? = null,
    val typeId: Int = 0,
    val stage: List<Int> = emptyList(),
    val color: String? = null,
    val image: String? = null
)

enum class CapsuleType(val id: Int) {
    COFFEE(0), GREEN_TEA(1), CHOCOLATE(2)
}