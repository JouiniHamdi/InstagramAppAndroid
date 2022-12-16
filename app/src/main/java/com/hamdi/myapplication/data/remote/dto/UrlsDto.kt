package com.hamdi.myapplication.data.remote.dto

import com.hamdi.myapplication.data.local.model.DatabaseUrl


data class UrlsDto(
    val full: String,
    val regular: String,
    val small: String,
)


fun UrlsDto.toDatabaseModel(): DatabaseUrl {
    return DatabaseUrl(
        full = full,
        regular = regular,
        small = small
    )
}

