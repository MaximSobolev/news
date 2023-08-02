package ru.test.nytn.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class MediaItem(
    val rank: Int,
    val subtype: String,
    val caption: String? = null,
    val credit: String? = null,
    val type: String,
    val url: String,
    val height: Int,
    val width: Int,
    val subType: String,
    @SerializedName("crop_name")
    val cropName: String,
    val legacy: Legacy? = null
) : Serializable