package ru.test.nytn.dto

import java.io.Serializable

data class Byline(
    val original: String,
    val person: ArrayList<Person>,
    val organization: String? = null
) : Serializable