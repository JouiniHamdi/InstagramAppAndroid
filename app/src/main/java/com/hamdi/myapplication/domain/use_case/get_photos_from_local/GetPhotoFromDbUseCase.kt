package com.hamdi.myapplication.domain.use_case.get_photos_from_local

import com.hamdi.myapplication.data.local.model.toDomainModel
import com.hamdi.myapplication.domain.model.Photo
import com.hamdi.myapplication.domain.repository.PhotoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetPhotoFromDbUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
      operator fun invoke() : Flow<List<Photo>> {
     return   repository.getPhotosFromDb().map { it -> it.map { it.toDomainModel() } }
    }
}