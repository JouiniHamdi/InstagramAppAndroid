package com.hamdi.myapplication.data.remote

import com.hamdi.myapplication.data.remote.dto.PhotoDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface UnsplashApi {

    @Headers("Content-Type:application/json", "Authorization:Client-ID YQF463zDlSir8LHUQoaQYAcnKkLZn3-QdA11YmEKQ6E")
    @GET("/photos")
    suspend fun getPhotos(): List<PhotoDto>


}