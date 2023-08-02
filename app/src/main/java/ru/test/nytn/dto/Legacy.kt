package ru.test.nytn.dto

import java.io.Serializable

data class Legacy (
    val xlarge : String? = null,
    val xlargewidth : Int? = null,
    val xlargeheight : Int? = null
        ) : Serializable