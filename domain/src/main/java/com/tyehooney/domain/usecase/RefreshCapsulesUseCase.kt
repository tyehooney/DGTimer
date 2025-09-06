package com.tyehooney.domain.usecase

import com.tyehooney.domain.repository.CapsuleRepository
import javax.inject.Inject

class RefreshCapsulesUseCase @Inject constructor(
    private val repository: CapsuleRepository
) {
    suspend operator fun invoke() = repository.refreshCapsules()
}