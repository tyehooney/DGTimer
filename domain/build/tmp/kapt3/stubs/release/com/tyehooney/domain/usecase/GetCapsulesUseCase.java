package com.tyehooney.domain.usecase;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0017\u0010\u0006\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\t\u0018\u00010\b0\u0007H\u0086\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/tyehooney/domain/usecase/GetCapsulesUseCase;", "", "repository", "Lcom/tyehooney/domain/repository/CapsuleRepository;", "<init>", "(Lcom/tyehooney/domain/repository/CapsuleRepository;)V", "invoke", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/tyehooney/domain/model/Capsule;", "domain_release"})
public final class GetCapsulesUseCase {
    @org.jetbrains.annotations.NotNull()
    private final com.tyehooney.domain.repository.CapsuleRepository repository = null;
    
    @javax.inject.Inject()
    public GetCapsulesUseCase(@org.jetbrains.annotations.NotNull()
    com.tyehooney.domain.repository.CapsuleRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.tyehooney.domain.model.Capsule>> invoke() {
        return null;
    }
}