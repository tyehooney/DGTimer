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
public final class UpdateCapsuleMajorUseCase_Factory implements Factory<UpdateCapsuleMajorUseCase> {
  private final Provider<CapsuleRepository> repositoryProvider;

  public UpdateCapsuleMajorUseCase_Factory(Provider<CapsuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateCapsuleMajorUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static UpdateCapsuleMajorUseCase_Factory create(
      Provider<CapsuleRepository> repositoryProvider) {
    return new UpdateCapsuleMajorUseCase_Factory(repositoryProvider);
  }

  public static UpdateCapsuleMajorUseCase newInstance(CapsuleRepository repository) {
    return new UpdateCapsuleMajorUseCase(repository);
  }
}
