package com.hamdi.myapplication.di

import com.hamdi.myapplication.data.local.dao.PhotoDao
import com.hamdi.myapplication.data.remote.UnsplashApi
import com.hamdi.myapplication.data.repository.PhotoRepositoryImpl
import com.hamdi.myapplication.domain.repository.PhotoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePhotoRepository(api: UnsplashApi, photoDao : PhotoDao): PhotoRepository {
        return PhotoRepositoryImpl(api,photoDao)
    }


}