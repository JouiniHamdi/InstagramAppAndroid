package com.hamdi.myapplication.domain.model

data class Photo(
    val id: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val description: String?,
    val altDescription: String?,
    val urls: Urls?,
    val user: User?,
    val isAlreadySeen: Boolean = false
)


