package com.tyehooney.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tyehooney.domain.model.Capsule as DomainCapsule

@Entity(tableName = "capsules")
data class CapsuleEntity(
    @PrimaryKey
    val id: Int = 0,
    val name: String = "",
    val typeId: Int = 0,
    val stage: List<Int> = emptyList(),
    val color: String? = null,
    val image: String? = null,
    @ColumnInfo(name = "major")
    val isMajor: Boolean = false
)

// Extension functions for mapping
fun CapsuleEntity.toDomain(): DomainCapsule = DomainCapsule(
    id = id,
    name = name,
    typeId = typeId,
    stage = stage,
    color = color,
    image = image,
    isMajor = isMajor
)

fun DomainCapsule.toEntity(): CapsuleEntity = CapsuleEntity(
    id = id,
    name = name,
    typeId = typeId,
    stage = stage,
    color = color,
    image = image,
    isMajor = isMajor
)