package com.hamdi.myapplication.data.local.model

import androidx.room.Entity
import com.hamdi.myapplication.domain.model.User

@Entity(tableName = "DatabaseUser")
data class DatabaseUser(
    val id: String,
    val name: String,
    val profileImage: DatabaseProfileImage
)

fun DatabaseUser.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        profileImage = profileImage.toDomainModel()
    )
}


