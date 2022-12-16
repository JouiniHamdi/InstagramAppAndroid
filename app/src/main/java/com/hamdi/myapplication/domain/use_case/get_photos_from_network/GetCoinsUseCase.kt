package com.hamdi.myapplication.domain.use_case.get_photos_from_network

import com.bumptech.glide.load.HttpException
import com.hamdi.myapplication.data.local.model.DatabasePhoto
import com.hamdi.myapplication.data.remote.dto.toDatabaseModel
import com.hamdi.myapplication.domain.repository.PhotoRepository
import com.hamdi.myapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetPhotosFromNetworkUseCase @Inject constructor(
    private val repository: PhotoRepository
) {
    operator fun invoke(): Flow<Resource<List<DatabasePhoto>>> = flow {
       try {
            emit(Resource.Loading<List<DatabasePhoto>>())
            val photos = repository.getPhotosFromNetwork().map { it.toDatabaseModel() }
            emit(Resource.Success<List<DatabasePhoto>>(photos))
        } catch(e: HttpException) {
            emit(Resource.Error<List<DatabasePhoto>>(e.localizedMessage ?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error<List<DatabasePhoto>>("Couldn't reach server. Check your internet connection."))
        }




    }
}