package com.tyehooney.domain.usecase

import com.tyehooney.domain.model.Capsule
import com.tyehooney.domain.repository.CapsuleRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCapsulesUseCase @Inject constructor(
    private val repository: CapsuleRepository
) {
    operator fun invoke(): Flow<List<Capsule>?> = repository.loadCapsules()
}