package com.tyehooney.domain.usecase

import com.tyehooney.domain.model.Capsule
import com.tyehooney.domain.repository.CapsuleRepository
import javax.inject.Inject

class SearchCapsulesByNameUseCase @Inject constructor(
    private val repository: CapsuleRepository
) {
    suspend operator fun invoke(name: String): List<Capsule>? = repository.searchCapsulesByName(name)
}