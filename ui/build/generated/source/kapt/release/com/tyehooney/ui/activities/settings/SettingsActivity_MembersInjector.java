package com.tyehooney.ui.activities.settings;

import com.tyehooney.ui.AppRater;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class SettingsActivity_MembersInjector implements MembersInjector<SettingsActivity> {
  private final Provider<AppRater> appRaterProvider;

  public SettingsActivity_MembersInjector(Provider<AppRater> appRaterProvider) {
    this.appRaterProvider = appRaterProvider;
  }

  public static MembersInjector<SettingsActivity> create(Provider<AppRater> appRaterProvider) {
    return new SettingsActivity_MembersInjector(appRaterProvider);
  }

  @Override
  public void injectMembers(SettingsActivity instance) {
    injectAppRater(instance, appRaterProvider.get());
  }

  @InjectedFieldSignature("com.tyehooney.ui.activities.settings.SettingsActivity.appRater")
  public static void injectAppRater(SettingsActivity instance, AppRater appRater) {
    instance.appRater = appRater;
  }
}
