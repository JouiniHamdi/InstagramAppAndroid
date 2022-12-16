package com.hamdi.myapplication.data.remote.dto

import com.hamdi.myapplication.data.local.model.DatabaseProfileImage


data class ProfileImageDto(
    val small: String,
    val medium: String,
    val large: String,
)



fun ProfileImageDto.toDatabaseModel(): DatabaseProfileImage {
    return DatabaseProfileImage(
        small = small,
        medium = medium,
        large = large
    )
}

