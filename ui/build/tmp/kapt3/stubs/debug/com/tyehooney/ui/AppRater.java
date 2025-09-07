package com.tyehooney.ui;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010\n\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/tyehooney/ui/AppRater;", "", "preferences", "Lcom/tyehooney/data/DGTimerPreferences;", "<init>", "(Lcom/tyehooney/data/DGTimerPreferences;)V", "set", "", "activity", "Landroid/app/Activity;", "showRateDialog", "Companion", "ui_debug"})
public final class AppRater {
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.data.DGTimerPreferences preferences = null;
    private static final int DAYS_UNTIL_PROMPT = 3;
    private static final int LAUNCHES_UNTIL_PROMPT = 3;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String GOOGLE_PLAY_LINK = "http://play.google.com/store/apps/details?id=com.tyehooney.dgtimer";
    @org.jetbrains.annotations.NotNull()
    public static final com.tyehooney.ui.AppRater.Companion Companion = null;
    
    @javax.inject.Inject()
    public AppRater(@org.jetbrains.annotations.NotNull()
    com.tyehooney.data.DGTimerPreferences preferences) {
        super();
    }
    
    public final void set(@org.jetbrains.annotations.NotNull()
    android.app.Activity activity) {
    }
    
    private final void showRateDialog(android.app.Activity activity) {
    }
    
    @kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/tyehooney/ui/AppRater$Companion;", "", "<init>", "()V", "DAYS_UNTIL_PROMPT", "", "LAUNCHES_UNTIL_PROMPT", "GOOGLE_PLAY_LINK", "", "launchGooglePlayForRating", "", "activity", "Landroid/app/Activity;", "ui_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final void launchGooglePlayForRating(@org.jetbrains.annotations.NotNull()
        android.app.Activity activity) {
        }
    }
}