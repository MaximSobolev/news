package ru.test.nytn.dto

import java.io.Serializable

data class Person(
    val firstname: String,
    val middlename: String? = null,
    val lastname: String,
    val qualifier: String? = null,
    val title: String? = null,
    val role: String,
    val organization: String,
    val rank: Int
) : Serializable