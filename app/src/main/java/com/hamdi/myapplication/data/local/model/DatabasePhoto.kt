package com.hamdi.myapplication.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.hamdi.myapplication.data.local.PhotoTypeConverter
import com.hamdi.myapplication.domain.model.Photo

@Entity(tableName = "photo_table")
@TypeConverters(PhotoTypeConverter::class)
data class DatabasePhoto(
    @PrimaryKey
    val id: String,
    val createdAt: String?,
    val updatedAt: String?,
    val description: String?,
    val altDescription: String?,
    val urls: DatabaseUrl?,
    val user: DatabaseUser?,
    val isAlreadySeen : Boolean = false
)


fun DatabasePhoto.toDomainModel(): Photo {
    return Photo(
        id = id,
        createdAt = createdAt,
        updatedAt = updatedAt,
        description = description,
        altDescription = altDescription,
        urls = urls?.toDomainModel(),
        user = user?.toDomainModel(),
        isAlreadySeen = isAlreadySeen

        )
}