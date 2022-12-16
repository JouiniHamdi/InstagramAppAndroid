package com.hamdi.myapplication.presentation.homeScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.hamdi.myapplication.domain.model.Photo
import com.hamdi.myapplication.domain.use_case.get_photos_from_local.GetPhotoFromDbUseCase
import com.hamdi.myapplication.domain.use_case.get_photos_from_network.GetPhotosFromNetworkUseCase
import com.hamdi.myapplication.domain.use_case.delete_all_photos_from_db.DeleteAllPhotosFromDbUseCase
import com.hamdi.myapplication.domain.use_case.save_photo_to_db.SavePhotoUseCase
import com.hamdi.myapplication.domain.use_case.update_photo_visibility_state.UpdatePhotoVisibilityState
import com.hamdi.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosListViewModel @Inject constructor(
    private val getPhotosFromNetworkUseCase: GetPhotosFromNetworkUseCase,
    private val savePhotoUseCase: SavePhotoUseCase,
    private val getPhotoFromDbUseCase: GetPhotoFromDbUseCase,
    private val deleteAllPhotosFromDbUseCase: DeleteAllPhotosFromDbUseCase,
    private val updatePhotoVisibilityState: UpdatePhotoVisibilityState

) : ViewModel() {


    val photosList: LiveData<List<Photo>> = getPhotoFromDbUseCase.invoke().asLiveData()

    init {
        getPhotos()
    }

    fun makePhotoAlreadyVisible(photoId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            updatePhotoVisibilityState.invoke(photoId)
        }
    }

    private fun getPhotos() {
        getPhotosFromNetworkUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> {
                    viewModelScope.launch(Dispatchers.IO) {
                        deleteAllPhotosFromDbUseCase.invoke()
                    }
                    savePhotoUseCase.invoke(result.data ?: emptyList())
                }
                is Resource.Error -> {}
                is Resource.Loading -> {}
            }
        }.launchIn(viewModelScope)
    }


}