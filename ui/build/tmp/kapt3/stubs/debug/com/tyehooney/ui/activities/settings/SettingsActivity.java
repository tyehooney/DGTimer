package com.tyehooney.ui.activities.settings;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\b\u0007\u0018\u0000 +2\u00020\u0001:\u0001+B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\b\u0010\"\u001a\u00020\u001fH\u0014J\b\u0010#\u001a\u00020\u001fH\u0002J\u0010\u0010$\u001a\u00020\u001f2\u0006\u0010%\u001a\u00020&H\u0002J\b\u0010\'\u001a\u00020\u001fH\u0002J\b\u0010(\u001a\u00020\u001fH\u0002J\b\u0010)\u001a\u00020\u001fH\u0002J\b\u0010*\u001a\u00020\u001fH\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0012\u001a\u00020\u00138BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0016\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015R?\u0010\u0017\u001a&\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u0019 \u001a*\u0012\u0012\u000e\b\u0001\u0012\n \u001a*\u0004\u0018\u00010\u00190\u00190\u00180\u00188BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001d\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006,"}, d2 = {"Lcom/tyehooney/ui/activities/settings/SettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "appRater", "Lcom/tyehooney/ui/AppRater;", "getAppRater", "()Lcom/tyehooney/ui/AppRater;", "setAppRater", "(Lcom/tyehooney/ui/AppRater;)V", "binding", "Lcom/tyehooney/ui/databinding/ActivitySettingsBinding;", "viewModel", "Lcom/tyehooney/ui/activities/settings/SettingsViewModel;", "getViewModel", "()Lcom/tyehooney/ui/activities/settings/SettingsViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "alarmPlayer", "Lcom/tyehooney/ui/utils/AlarmPlayerWrapper;", "getAlarmPlayer", "()Lcom/tyehooney/ui/utils/AlarmPlayerWrapper;", "alarmPlayer$delegate", "strAlarms", "", "", "kotlin.jvm.PlatformType", "getStrAlarms", "()[Ljava/lang/String;", "strAlarms$delegate", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "initView", "ringAlarm", "alarm", "", "vibrate", "showChooseAlarmDialog", "saveAndFinish", "initObservers", "Companion", "ui_debug"})
public final class SettingsActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject()
    public com.tyehooney.ui.AppRater appRater;
    private com.tyehooney.ui.databinding.ActivitySettingsBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy alarmPlayer$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy strAlarms$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.tyehooney.ui.activities.settings.SettingsActivity.Companion Companion = null;
    
    public SettingsActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.tyehooney.ui.AppRater getAppRater() {
        return null;
    }
    
    public final void setAppRater(@org.jetbrains.annotations.NotNull()
    com.tyehooney.ui.AppRater p0) {
    }
    
    private final com.tyehooney.ui.activities.settings.SettingsViewModel getViewModel() {
        return null;
    }
    
    private final com.tyehooney.ui.utils.AlarmPlayerWrapper getAlarmPlayer() {
        return null;
    }
    
    private final java.lang.String[] getStrAlarms() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void initView() {
    }
    
    private final void ringAlarm(int alarm) {
    }
    
    private final void vibrate() {
    }
    
    private final void showChooseAlarmDialog() {
    }
    
    private final void saveAndFinish() {
    }
    
    private final void initObservers() {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007\u00a8\u0006\b"}, d2 = {"Lcom/tyehooney/ui/activities/settings/SettingsActivity$Companion;", "", "<init>", "()V", "createSettingsActivityIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.content.Intent createSettingsActivityIntent(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}