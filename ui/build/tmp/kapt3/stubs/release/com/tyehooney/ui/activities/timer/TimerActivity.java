package com.tyehooney.ui.activities.timer;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0014J\b\u0010\u001b\u001a\u00020\u0014H\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0003J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\u0010\u0010\u001f\u001a\u00020\u00142\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020\u0014H\u0002J\u0010\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010&\u001a\u00020\u00142\u0006\u0010\'\u001a\u00020\u0012H\u0002J\u0016\u0010(\u001a\u00020\u00142\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006-"}, d2 = {"Lcom/tyehooney/ui/activities/timer/TimerActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "binding", "Lcom/tyehooney/ui/databinding/ActivityTimerBinding;", "viewModel", "Lcom/tyehooney/ui/activities/timer/TimerViewModel;", "getViewModel", "()Lcom/tyehooney/ui/activities/timer/TimerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "alarmPlayer", "Lcom/tyehooney/ui/utils/AlarmPlayerWrapper;", "getAlarmPlayer", "()Lcom/tyehooney/ui/utils/AlarmPlayerWrapper;", "alarmPlayer$delegate", "hasConfigurationChanged", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onConfigurationChanged", "newConfig", "Landroid/content/res/Configuration;", "onStop", "onDestroy", "setCapsuleData", "initView", "initObservers", "updateCapsuleUi", "capsule", "Lcom/tyehooney/domain/model/Capsule;", "handlePlayButtonClickEvent", "updateCounterState", "counterState", "Lcom/tyehooney/ui/activities/timer/CounterState;", "updateAlarmState", "isAlarmOn", "updateCounterViews", "counters", "", "Lcom/tyehooney/ui/activities/timer/Counter;", "Companion", "ui_release"})
public final class TimerActivity extends androidx.appcompat.app.AppCompatActivity {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_CAPSULE_ID = "capsuleId";
    private com.tyehooney.ui.databinding.ActivityTimerBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy alarmPlayer$delegate = null;
    private boolean hasConfigurationChanged = false;
    @org.jetbrains.annotations.NotNull()
    public static final com.tyehooney.ui.activities.timer.TimerActivity.Companion Companion = null;
    
    public TimerActivity() {
        super();
    }
    
    private final com.tyehooney.ui.activities.timer.TimerViewModel getViewModel() {
        return null;
    }
    
    private final com.tyehooney.ui.utils.AlarmPlayerWrapper getAlarmPlayer() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    public void onConfigurationChanged(@org.jetbrains.annotations.NotNull()
    android.content.res.Configuration newConfig) {
    }
    
    @java.lang.Override()
    protected void onStop() {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void setCapsuleData() {
    }
    
    @android.annotation.SuppressLint(value = {"SetTextI18n"})
    private final void initView() {
    }
    
    private final void initObservers() {
    }
    
    private final void updateCapsuleUi(com.tyehooney.domain.model.Capsule capsule) {
    }
    
    private final void handlePlayButtonClickEvent() {
    }
    
    private final void updateCounterState(com.tyehooney.ui.activities.timer.CounterState counterState) {
    }
    
    private final void updateAlarmState(boolean isAlarmOn) {
    }
    
    private final void updateCounterViews(java.util.List<com.tyehooney.ui.activities.timer.Counter> counters) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/tyehooney/ui/activities/timer/TimerActivity$Companion;", "", "<init>", "()V", "KEY_CAPSULE_ID", "", "createTimerActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "capsuleId", "", "ui_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createTimerActivityIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context, int capsuleId) {
            return null;
        }
    }
}