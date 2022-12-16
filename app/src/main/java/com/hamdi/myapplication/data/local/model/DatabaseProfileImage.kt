package com.hamdi.myapplication.data.local.model

import androidx.room.Entity
import com.hamdi.myapplication.domain.model.ProfileImage

@Entity(tableName = "DatabaseProfileImage")
data class DatabaseProfileImage(
    val small: String,
    val medium: String,
    val large: String,
)

fun DatabaseProfileImage.toDomainModel(): ProfileImage {
    return ProfileImage(
        small = small,
        medium = medium,
        large = large
    )
}

