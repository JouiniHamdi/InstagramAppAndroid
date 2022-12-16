package com.hamdi.myapplication.domain.repository

import com.hamdi.myapplication.data.local.model.DatabasePhoto
import com.hamdi.myapplication.data.remote.dto.PhotoDto
import kotlinx.coroutines.flow.Flow

interface PhotoRepository {

    suspend fun getPhotosFromNetwork(): List<PhotoDto>
    suspend fun savePhotosToDb(photoList : List<DatabasePhoto>)
    fun getPhotosFromDb() : Flow<List<DatabasePhoto>>
    fun deleteAllPhotosFromDb()
    fun updatePhotoVisibilityState(photoId: String)




}