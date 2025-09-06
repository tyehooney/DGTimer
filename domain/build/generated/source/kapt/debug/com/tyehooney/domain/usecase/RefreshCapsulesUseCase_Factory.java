package com.tyehooney.domain.usecase;

import com.tyehooney.domain.repository.CapsuleRepository;
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
public final class RefreshCapsulesUseCase_Factory implements Factory<RefreshCapsulesUseCase> {
  private final Provider<CapsuleRepository> repositoryProvider;

  public RefreshCapsulesUseCase_Factory(Provider<CapsuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RefreshCapsulesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static RefreshCapsulesUseCase_Factory create(
      Provider<CapsuleRepository> repositoryProvider) {
    return new RefreshCapsulesUseCase_Factory(repositoryProvider);
  }

  public static RefreshCapsulesUseCase newInstance(CapsuleRepository repository) {
    return new RefreshCapsulesUseCase(repository);
  }
}
