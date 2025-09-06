package com.tyehooney.ui.activities.timer

import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    
    // Simple minimal implementation
    fun setCapsuleData(capsuleId: Int) {
        println("TimerViewModel setCapsuleData called with ID: $capsuleId")
    }
}