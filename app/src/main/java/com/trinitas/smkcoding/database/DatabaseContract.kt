package com.trinitas.smkcoding.database

import android.icu.text.CaseMap
import android.media.MediaDescription
import android.media.Rating

data class DatabaseContract(
    val id: Long? = null,
    val title: String? = null,
    val posterPath: String? = null,
    val rating: Double? = null,
    val description: String? = null
)