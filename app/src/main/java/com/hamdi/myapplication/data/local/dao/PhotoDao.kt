package com.hamdi.myapplication.data.local.dao

import androidx.room.*
import com.hamdi.myapplication.data.local.model.DatabasePhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photo_table")
    fun getAllPhotos(): Flow<List<DatabasePhoto>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPhotos(photosList: List<DatabasePhoto>)


    @Query("DELETE FROM photo_table")
    fun deleteAllPhotos()


  @Query("UPDATE   photo_table SET isAlreadySeen = 1 WHERE id like :photoId")
    fun updatePhotoVisibilityState( photoId : String)




}