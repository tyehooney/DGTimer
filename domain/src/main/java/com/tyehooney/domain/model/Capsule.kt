package com.tyehooney.domain.model

import android.graphics.Color
import com.tyehooney.common.R

data class Capsule(
    val id: Int = 0,
    val name: String = "",
    val typeId: Int = 0,
    val stage: List<Int> = emptyList(),
    val color: String? = null,
    val image: String? = null,
    val isMajor: Boolean = false
) {
    val colorAsInt: Int
        get() = Color.parseColor(color)

    val typeStringResId: Int
        get() = when (typeId.getCapsuleType()) {
            CapsuleType.COFFEE -> R.string.capsule_type_coffee
            CapsuleType.GREEN_TEA -> R.string.capsule_type_green_tea
            CapsuleType.CHOCOLATE -> R.string.capsule_type_chocolate
        }

    private fun Int.getCapsuleType(): CapsuleType =
        CapsuleType.entries.find { it.id == this } ?: CapsuleType.COFFEE
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