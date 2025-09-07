package com.tyehooney.ui.activities.timer

import com.tyehooney.domain.model.CapsuleType

data class Counter(
    val capsuleType: CapsuleType,
    val totalTime: Long,
    val index: Int,
    val isActive: Boolean,
    val currentTime: Long = totalTime,
)
