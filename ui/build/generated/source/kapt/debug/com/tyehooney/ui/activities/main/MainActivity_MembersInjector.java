package com.tyehooney.ui.activities.main;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<AppRater> appRaterProvider;

  public MainActivity_MembersInjector(Provider<AppRater> appRaterProvider) {
    this.appRaterProvider = appRaterProvider;
  }

  public static MembersInjector<MainActivity> create(Provider<AppRater> appRaterProvider) {
    return new MainActivity_MembersInjector(appRaterProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectAppRater(instance, appRaterProvider.get());
  }

  @InjectedFieldSignature("com.tyehooney.ui.activities.main.MainActivity.appRater")
  public static void injectAppRater(MainActivity instance, AppRater appRater) {
    instance.appRater = appRater;
  }
}
