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
public final class GetCapsulesUseCase_Factory implements Factory<GetCapsulesUseCase> {
  private final Provider<CapsuleRepository> repositoryProvider;

  public GetCapsulesUseCase_Factory(Provider<CapsuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetCapsulesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetCapsulesUseCase_Factory create(Provider<CapsuleRepository> repositoryProvider) {
    return new GetCapsulesUseCase_Factory(repositoryProvider);
  }

  public static GetCapsulesUseCase newInstance(CapsuleRepository repository) {
    return new GetCapsulesUseCase(repository);
  }
}
