package com.hamdi.myapplication.data.remote.dto

import com.hamdi.myapplication.data.local.model.DatabasePhoto
import com.google.gson.annotations.SerializedName

data class PhotoDto(
    @SerializedName("id") val
    id: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    val description: String,
    @SerializedName("alt_description")
    val altDescription: String,
    val urls: UrlsDto,
    val user: UserDto,
)


fun PhotoDto.toDatabaseModel(): DatabasePhoto {
    return DatabasePhoto(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        description = description,
        altDescription = altDescription,
        urls = urls.toDatabaseModel(),
        user = user.toDatabaseModel()
    )
}



