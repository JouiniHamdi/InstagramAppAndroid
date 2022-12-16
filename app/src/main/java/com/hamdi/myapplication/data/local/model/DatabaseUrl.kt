package com.hamdi.myapplication.data.local.model

import androidx.room.Entity
import com.hamdi.myapplication.domain.model.Urls

@Entity(tableName = "DatabaseUrl")
data class DatabaseUrl(
   val full: String?,
   val regular: String?,
   val small: String?,
)

fun DatabaseUrl.toDomainModel(): Urls {
   return Urls(
      full = full,
      regular = regular,
      small = small
   )
}
