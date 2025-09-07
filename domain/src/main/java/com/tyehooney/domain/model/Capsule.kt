package com.tyehooney.domain.model

data class Capsule(
    val id: Int = 0,
    val name: String = "",
    val typeId: Int = 0,
    val stage: List<Int> = emptyList(),
    val color: String? = null,
    val image: String? = null,
    val isMajor: Boolean = false
) {
    val colorHex: String
        get() = color ?: "#8B4513" // Default brown color

    val capsuleType: CapsuleType
        get() = typeId.getCapsuleType()

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