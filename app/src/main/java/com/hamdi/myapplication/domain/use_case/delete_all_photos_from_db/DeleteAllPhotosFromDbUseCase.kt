package com.hamdi.myapplication.domain.use_case.delete_all_photos_from_db

import com.hamdi.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class DeleteAllPhotosFromDbUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    suspend operator fun invoke()  {
        repository.deleteAllPhotosFromDb()
    }
}