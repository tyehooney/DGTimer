package com.tyehooney.ui.activities.timer;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eJ\u001a\u0010\u000f\u001a\u00020\u000b2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tJ\u0010\u0010\u0010\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/tyehooney/ui/activities/timer/CounterView;", "Landroid/widget/LinearLayout;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "binding", "Lcom/tyehooney/ui/databinding/ViewCounterBinding;", "onClick", "Lkotlin/Function1;", "", "", "updateCounter", "counter", "Lcom/tyehooney/ui/activities/timer/Counter;", "setOnClickListener", "getTextColorByActiveState", "isActive", "", "getCapsuleTypeString", "", "capsuleType", "Lcom/tyehooney/domain/model/CapsuleType;", "ui_debug"})
public final class CounterView extends android.widget.LinearLayout {
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.ui.databinding.ViewCounterBinding binding = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClick;
    
    public CounterView(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super(null);
    }
    
    public final void updateCounter(@org.jetbrains.annotations.NotNull()
    com.tyehooney.ui.activities.timer.Counter counter) {
    }
    
    public final void setOnClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onClick) {
    }
    
    private final int getTextColorByActiveState(boolean isActive) {
        return 0;
    }
    
    private final java.lang.String getCapsuleTypeString(android.content.Context context, com.tyehooney.domain.model.CapsuleType capsuleType) {
        return null;
    }
}