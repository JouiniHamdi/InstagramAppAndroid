package com.hamdi.myapplication.data.local

import androidx.room.TypeConverter
import com.hamdi.myapplication.data.local.model.DatabaseProfileImage
import com.hamdi.myapplication.data.local.model.DatabaseUrl
import com.hamdi.myapplication.data.local.model.DatabaseUser
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PhotoTypeConverter {

    @TypeConverter
    fun DatabaseUrlFromString(value: String?):  DatabaseUrl?  {
        val listType = object : TypeToken< DatabaseUrl?> () {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun DatabaseUrlFromListToString(list: DatabaseUrl? ): String? {
        val gson = Gson()
        return gson.toJson(list)
    }



    //---------------------DatabaseUser--------------------------


    @TypeConverter
    fun DatabaseUserFromString(value: String?):  DatabaseUser?  {
        val listType = object : TypeToken<DatabaseUser?> () {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun DatabaseUserFromListToString(list: DatabaseUser? ): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
    //---------------------DatabaseProfileImage--------------------------


    @TypeConverter
    fun DatabaseProfileImageFromString(value: String?):  DatabaseProfileImage?  {
        val listType = object : TypeToken<DatabaseProfileImage?> () {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun DatabaseProfileImageFromListToString(list: DatabaseProfileImage? ): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


    }
