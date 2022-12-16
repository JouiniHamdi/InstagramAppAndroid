package com.hamdi.myapplication.domain.model

import com.hamdi.myapplication.domain.model.ProfileImage

data class User(
    val id: String,
    val name: String,
    val profileImage: ProfileImage
)


