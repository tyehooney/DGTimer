package com.tyehooney.ui.activities.main;

import com.tyehooney.data.DGTimerPreferences;
import com.tyehooney.domain.usecase.GetCapsulesUseCase;
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
public final class MainViewModel_Factory implements Factory<MainViewModel> {
  private final Provider<GetCapsulesUseCase> getCapsulesUseCaseProvider;

  private final Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider;

  private final Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider;

  private final Provider<DGTimerPreferences> preferencesProvider;

  public MainViewModel_Factory(Provider<GetCapsulesUseCase> getCapsulesUseCaseProvider,
      Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider,
      Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider,
      Provider<DGTimerPreferences> preferencesProvider) {
    this.getCapsulesUseCaseProvider = getCapsulesUseCaseProvider;
    this.refreshCapsulesUseCaseProvider = refreshCapsulesUseCaseProvider;
    this.updateCapsuleMajorUseCaseProvider = updateCapsuleMajorUseCaseProvider;
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public MainViewModel get() {
    return newInstance(getCapsulesUseCaseProvider.get(), refreshCapsulesUseCaseProvider.get(), updateCapsuleMajorUseCaseProvider.get(), preferencesProvider.get());
  }

  public static MainViewModel_Factory create(
      Provider<GetCapsulesUseCase> getCapsulesUseCaseProvider,
      Provider<RefreshCapsulesUseCase> refreshCapsulesUseCaseProvider,
      Provider<UpdateCapsuleMajorUseCase> updateCapsuleMajorUseCaseProvider,
      Provider<DGTimerPreferences> preferencesProvider) {
    return new MainViewModel_Factory(getCapsulesUseCaseProvider, refreshCapsulesUseCaseProvider, updateCapsuleMajorUseCaseProvider, preferencesProvider);
  }

  public static MainViewModel newInstance(GetCapsulesUseCase getCapsulesUseCase,
      RefreshCapsulesUseCase refreshCapsulesUseCase,
      UpdateCapsuleMajorUseCase updateCapsuleMajorUseCase, DGTimerPreferences preferences) {
    return new MainViewModel(getCapsulesUseCase, refreshCapsulesUseCase, updateCapsuleMajorUseCase, preferences);
  }
}
