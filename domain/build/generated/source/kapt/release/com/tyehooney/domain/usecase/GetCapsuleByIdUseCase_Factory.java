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
public final class GetCapsuleByIdUseCase_Factory implements Factory<GetCapsuleByIdUseCase> {
  private final Provider<CapsuleRepository> repositoryProvider;

  public GetCapsuleByIdUseCase_Factory(Provider<CapsuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCapsuleByIdUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCapsuleByIdUseCase_Factory create(
      Provider<CapsuleRepository> repositoryProvider) {
    return new GetCapsuleByIdUseCase_Factory(repositoryProvider);
  }

  public static GetCapsuleByIdUseCase newInstance(CapsuleRepository repository) {
    return new GetCapsuleByIdUseCase(repository);
  }
}
