package com.hamdi.myapplication.domain.use_case.save_photo_to_db

import com.hamdi.myapplication.data.local.model.DatabasePhoto
import com.hamdi.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class SavePhotoUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke(photoList : List<DatabasePhoto>)  {
        repository.savePhotosToDb(photoList)
    }
}