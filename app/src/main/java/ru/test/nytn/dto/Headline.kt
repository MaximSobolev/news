package ru.test.nytn.dto

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Headline(
    val main: String,
    val kicker: String? = null,
    @SerializedName("content_kicker")
    val contentKicker: String? = null,
    @SerializedName("print_headline")
    val printHeadline: String,
    val name: String? = null,
    val seo: String? = null,
    val sub: String?
) : Serializable