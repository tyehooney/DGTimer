package com.tyehooney.ui.activities.timer;

import com.tyehooney.data.DGTimerPreferences;
import com.tyehooney.domain.usecase.GetCapsuleByIdUseCase;
import com.tyehooney.domain.usecase.RefreshCapsulesUseCase;
import com.tyehooney.domain.usecase.UpdateCapsuleMajorUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class TimerViewModel_Factory implements Factory<TimerViewModel> {
  private final Provider<GetCapsuleByIdUseCase> getCapsuleByIdUseCaseProvider;

  private final Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider;

  private final Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider;

  private final Provider<DGTimerPreferences> preferencesProvider;

  public TimerViewModel_Factory(Provider<GetCapsuleByIdUseCase> getCapsuleByIdUseCaseProvider,
      Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider,
      Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider,
      Provider<DGTimerPreferences> preferencesProvider) {
    this.getCapsuleByIdUseCaseProvider = getCapsuleByIdUseCaseProvider;
    this.updateCapsuleMajorUseCaseProvider = updateCapsuleMajorUseCaseProvider;
    this.refreshCapsulesUseCaseProvider = refreshCapsulesUseCaseProvider;
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public TimerViewModel get() {
    return newInstance(getCapsuleByIdUseCaseProvider.get(), updateCapsuleMajorUseCaseProvider.get(), refreshCapsulesUseCaseProvider.get(), preferencesProvider.get());
  }

  public static TimerViewModel_Factory create(
      Provider<GetCapsuleByIdUseCase> getCapsuleByIdUseCaseProvider,
      Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider,
      Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider,
      Provider<DGTimerPreferences> preferencesProvider) {
    return new TimerViewModel_Factory(getCapsuleByIdUseCaseProvider, updateCapsuleMajorUseCaseProvider, refreshCapsulesUseCaseProvider, preferencesProvider);
  }

  public static TimerViewModel newInstance(GetCapsuleByIdUseCase getCapsuleByIdUseCase,
      UpdateCapsuleMajorUseCase updateCapsuleMajorUseCase,
      RefreshCapsulesUseCase refreshCapsulesUseCase, DGTimerPreferences preferences) {
    return new TimerViewModel(getCapsuleByIdUseCase, updateCapsuleMajorUseCase, refreshCapsulesUseCase, preferences);
  }
}
