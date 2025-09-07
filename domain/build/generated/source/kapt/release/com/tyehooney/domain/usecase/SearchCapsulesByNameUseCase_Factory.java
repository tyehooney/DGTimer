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
public final class SearchCapsulesByNameUseCase_Factory implements Factory<SearchCapsulesByNameUseCase> {
  private final Provider<CapsuleRepository> repositoryProvider;

  public SearchCapsulesByNameUseCase_Factory(Provider<CapsuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public SearchCapsulesByNameUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static SearchCapsulesByNameUseCase_Factory create(
      Provider<CapsuleRepository> repositoryProvider) {
    return new SearchCapsulesByNameUseCase_Factory(repositoryProvider);
  }

  public static SearchCapsulesByNameUseCase newInstance(CapsuleRepository repository) {
    return new SearchCapsulesByNameUseCase(repository);
  }
}
