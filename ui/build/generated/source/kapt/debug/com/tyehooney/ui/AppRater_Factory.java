package com.tyehooney.ui;

import com.tyehooney.data.DGTimerPreferences;
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
public final class AppRater_Factory implements Factory<AppRater> {
  private final Provider<DGTimerPreferences> preferencesProvider;

  public AppRater_Factory(Provider<DGTimerPreferences> preferencesProvider) {
    this.preferencesProvider = preferencesProvider;
  }

  @Override
  public AppRater get() {
    return newInstance(preferencesProvider.get());
  }

  public static AppRater_Factory create(Provider<DGTimerPreferences> preferencesProvider) {
    return new AppRater_Factory(preferencesProvider);
  }

  public static AppRater newInstance(DGTimerPreferences preferences) {
    return new AppRater(preferences);
  }
}
