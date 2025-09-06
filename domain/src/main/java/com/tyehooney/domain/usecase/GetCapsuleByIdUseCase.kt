package com.tyehooney.domain.usecase

import com.tyehooney.domain.model.Capsule
import com.tyehooney.domain.repository.CapsuleRepository
import javax.inject.Inject

class GetCapsuleByIdUseCase @Inject constructor(
    private val repository: CapsuleRepository
) {
    suspend operator fun invoke(id: Int): Capsule? = repository.getCapsuleById(id)
}