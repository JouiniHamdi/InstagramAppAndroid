package com.hamdi.myapplication.domain.use_case.update_photo_visibility_state

import com.hamdi.myapplication.domain.repository.PhotoRepository
import javax.inject.Inject

class UpdatePhotoVisibilityState @Inject constructor(
    private val repository: PhotoRepository
) {
      operator fun invoke(photoId : String)  {
        repository.updatePhotoVisibilityState(photoId)
    }
}