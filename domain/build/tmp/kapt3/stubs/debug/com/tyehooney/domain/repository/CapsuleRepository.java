package com.tyehooney.domain.repository;

@kotlin.Metadata(mv = {2, 1, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u000e\u0010\u0002\u001a\u00020\u0003H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u0006H&J\u0016\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\u00a6@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007H\u00a6@\u00a2\u0006\u0002\u0010\u0004J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u00a6@\u00a2\u0006\u0002\u0010\u0014J\u001e\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00072\u0006\u0010\r\u001a\u00020\u000eH\u00a6@\u00a2\u0006\u0002\u0010\u000fJ\u0018\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u00062\u0006\u0010\u0012\u001a\u00020\u0013H&J\u0016\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0013H\u00a6@\u00a2\u0006\u0002\u0010\u0014\u00a8\u0006\u0019"}, d2 = {"Lcom/tyehooney/domain/repository/CapsuleRepository;", "", "refreshCapsules", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadCapsules", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/tyehooney/domain/model/Capsule;", "addCapsule", "capsule", "(Lcom/tyehooney/domain/model/Capsule;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCapsuleByName", "name", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getStarredCapsules", "getCapsuleById", "id", "", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchCapsulesByName", "searchCapsuleById", "updateCapsuleMajor", "capsuleId", "domain_debug"})
public abstract interface CapsuleRepository {
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object refreshCapsules(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.tyehooney.domain.model.Capsule>> loadCapsules();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addCapsule(@org.jetbrains.annotations.NotNull()
    com.tyehooney.domain.model.Capsule capsule, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCapsuleByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tyehooney.domain.model.Capsule>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getStarredCapsules(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tyehooney.domain.model.Capsule>> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCapsuleById(int id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.tyehooney.domain.model.Capsule> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object searchCapsulesByName(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.tyehooney.domain.model.Capsule>> $completion);
    
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.tyehooney.domain.model.Capsule> searchCapsuleById(int id);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object updateCapsuleMajor(int capsuleId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}