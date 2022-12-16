package com.hamdi.myapplication.data.repository

import com.hamdi.myapplication.data.local.dao.PhotoDao
import com.hamdi.myapplication.data.local.model.DatabasePhoto
import com.hamdi.myapplication.data.remote.UnsplashApi
import com.hamdi.myapplication.data.remote.dto.PhotoDto
import com.hamdi.myapplication.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val api: UnsplashApi,
    private val photoDao: PhotoDao
) : PhotoRepository {


    override suspend fun getPhotosFromNetwork(): List<PhotoDto> {
        return api.getPhotos()
    }

    override suspend fun savePhotosToDb(photosList: List<DatabasePhoto>) {
        photoDao.insertAllPhotos(photosList)
    }

    override  fun getPhotosFromDb(): Flow<List<DatabasePhoto>> {
      return  photoDao.getAllPhotos()
    }


    override fun deleteAllPhotosFromDb() {
        photoDao.deleteAllPhotos()
    }


 override fun updatePhotoVisibilityState(photoId: String) {
        photoDao.updatePhotoVisibilityState(photoId)
    }


}