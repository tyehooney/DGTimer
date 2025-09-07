package com.tyehooney.ui.activities.settings;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bJ\u000e\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\bJ\u0006\u0010\u001f\u001a\u00020\u000eJ\u0006\u0010 \u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\fR\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\fR\u001a\u0010\u0018\u001a\u00020\bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c\u00a8\u0006!"}, d2 = {"Lcom/tyehooney/ui/activities/settings/SettingsViewModel;", "Landroidx/lifecycle/ViewModel;", "preferences", "Lcom/tyehooney/data/DGTimerPreferences;", "<init>", "(Lcom/tyehooney/data/DGTimerPreferences;)V", "_volume", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "volume", "Lkotlinx/coroutines/flow/StateFlow;", "getVolume", "()Lkotlinx/coroutines/flow/StateFlow;", "setVolume", "", "newVolume", "_amplitude", "amplitude", "getAmplitude", "setAmplitude", "newAmplitude", "_alarm", "alarm", "getAlarm", "selectedAlarmIndex", "getSelectedAlarmIndex", "()I", "setSelectedAlarmIndex", "(I)V", "setAlarm", "newAlarm", "resetAllSettings", "saveSettings", "ui_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class SettingsViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.data.DGTimerPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _volume = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> volume = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _amplitude = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> amplitude = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Integer> _alarm = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> alarm = null;
    private int selectedAlarmIndex;
    
    @javax.inject.Inject()
    public SettingsViewModel(@org.jetbrains.annotations.NotNull()
    com.tyehooney.data.DGTimerPreferences preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getVolume() {
        return null;
    }
    
    public final void setVolume(int newVolume) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getAmplitude() {
        return null;
    }
    
    public final void setAmplitude(int newAmplitude) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Integer> getAlarm() {
        return null;
    }
    
    public final int getSelectedAlarmIndex() {
        return 0;
    }
    
    public final void setSelectedAlarmIndex(int p0) {
    }
    
    public final void setAlarm(int newAlarm) {
    }
    
    public final void resetAllSettings() {
    }
    
    public final void saveSettings() {
    }
}