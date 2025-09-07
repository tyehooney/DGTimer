package com.tyehooney.ui.activities.timer;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0010\t\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010+\u001a\u00020,J\u000e\u0010-\u001a\u00020,2\u0006\u0010.\u001a\u00020\"J\u0006\u0010/\u001a\u00020,J\u000e\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020\u001fJ\u000e\u00102\u001a\u00020,2\u0006\u00103\u001a\u00020\"J\u0006\u00104\u001a\u00020,J\u0018\u00105\u001a\u00020,2\u0006\u00106\u001a\u00020\"2\u0006\u00107\u001a\u000208H\u0002J\u0006\u00109\u001a\u00020,J\u0006\u0010:\u001a\u00020,J\n\u0010;\u001a\u0004\u0018\u00010\u0015H\u0002J\b\u0010<\u001a\u00020,H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0012R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u0010\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0012R\u0011\u0010!\u001a\u00020\"8F\u00a2\u0006\u0006\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&8F\u00a2\u0006\u0006\u001a\u0004\b\'\u0010(R\u0011\u0010)\u001a\u00020\"8F\u00a2\u0006\u0006\u001a\u0004\b*\u0010$\u00a8\u0006="}, d2 = {"Lcom/tyehooney/ui/activities/timer/TimerViewModel;", "Landroidx/lifecycle/ViewModel;", "getCapsuleByIdUseCase", "Lcom/tyehooney/domain/usecase/GetCapsuleByIdUseCase;", "updateCapsuleMajorUseCase", "Lcom/tyehooney/domain/usecase/UpdateCapsuleMajorUseCase;", "refreshCapsulesUseCase", "Lcom/tyehooney/domain/usecase/RefreshCapsulesUseCase;", "preferences", "Lcom/tyehooney/data/DGTimerPreferences;", "<init>", "(Lcom/tyehooney/domain/usecase/GetCapsuleByIdUseCase;Lcom/tyehooney/domain/usecase/UpdateCapsuleMajorUseCase;Lcom/tyehooney/domain/usecase/RefreshCapsulesUseCase;Lcom/tyehooney/data/DGTimerPreferences;)V", "_capsuleFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/tyehooney/domain/model/Capsule;", "capsuleFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getCapsuleFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "_counters", "", "Lcom/tyehooney/ui/activities/timer/Counter;", "counters", "getCounters", "countDownTimer", "Landroid/os/CountDownTimer;", "_counterState", "Lcom/tyehooney/ui/activities/timer/CounterState;", "counterState", "getCounterState", "_isAlarmOn", "", "isAlarmOn", "alarmAmplitude", "", "getAlarmAmplitude", "()I", "alarmVolume", "", "getAlarmVolume", "()F", "alarm", "getAlarm", "refreshCapsuleData", "", "setCapsuleData", "capsuleId", "toggleCapsuleMajor", "updateAlarmOn", "isOn", "setActiveCounter", "activeIndex", "playCountDownTimer", "updateCounterTime", "index", "timeInMillis", "", "pauseCountDownTimer", "resetCountDownTimer", "getActiveCounter", "onCleared", "ui_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class TimerViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.domain.usecase.GetCapsuleByIdUseCase getCapsuleByIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.domain.usecase.UpdateCapsuleMajorUseCase updateCapsuleMajorUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.domain.usecase.RefreshCapsulesUseCase refreshCapsulesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.data.DGTimerPreferences preferences = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tyehooney.domain.model.Capsule> _capsuleFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tyehooney.domain.model.Capsule> capsuleFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.tyehooney.ui.activities.timer.Counter>> _counters = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tyehooney.ui.activities.timer.Counter>> counters = null;
    @org.jetbrains.annotations.Nullable()
    private android.os.CountDownTimer countDownTimer;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.tyehooney.ui.activities.timer.CounterState> _counterState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.tyehooney.ui.activities.timer.CounterState> counterState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isAlarmOn = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAlarmOn = null;
    
    @javax.inject.Inject()
    public TimerViewModel(@org.jetbrains.annotations.NotNull()
    com.tyehooney.domain.usecase.GetCapsuleByIdUseCase getCapsuleByIdUseCase, @org.jetbrains.annotations.NotNull()
    com.tyehooney.domain.usecase.UpdateCapsuleMajorUseCase updateCapsuleMajorUseCase, @org.jetbrains.annotations.NotNull()
    com.tyehooney.domain.usecase.RefreshCapsulesUseCase refreshCapsulesUseCase, @org.jetbrains.annotations.NotNull()
    com.tyehooney.data.DGTimerPreferences preferences) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tyehooney.domain.model.Capsule> getCapsuleFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.tyehooney.ui.activities.timer.Counter>> getCounters() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.tyehooney.ui.activities.timer.CounterState> getCounterState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isAlarmOn() {
        return null;
    }
    
    public final int getAlarmAmplitude() {
        return 0;
    }
    
    public final float getAlarmVolume() {
        return 0.0F;
    }
    
    public final int getAlarm() {
        return 0;
    }
    
    public final void refreshCapsuleData() {
    }
    
    public final void setCapsuleData(int capsuleId) {
    }
    
    public final void toggleCapsuleMajor() {
    }
    
    public final void updateAlarmOn(boolean isOn) {
    }
    
    public final void setActiveCounter(int activeIndex) {
    }
    
    public final void playCountDownTimer() {
    }
    
    private final void updateCounterTime(int index, long timeInMillis) {
    }
    
    public final void pauseCountDownTimer() {
    }
    
    public final void resetCountDownTimer() {
    }
    
    private final com.tyehooney.ui.activities.timer.Counter getActiveCounter() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}