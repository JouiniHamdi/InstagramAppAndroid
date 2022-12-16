package com.hamdi.myapplication.data.remote.dto

import com.hamdi.myapplication.data.local.model.DatabaseUser
import com.google.gson.annotations.SerializedName

data class UserDto(
    val id: String,
    val name: String,
    @SerializedName("profile_image")
    val profileImage: ProfileImageDto
)



fun UserDto.toDatabaseModel(): DatabaseUser {
    return DatabaseUser(
        id = id,
        name = name,
        profileImage = profileImage.toDatabaseModel()
    )
}




