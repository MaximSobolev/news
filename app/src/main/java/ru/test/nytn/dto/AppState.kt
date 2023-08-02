package ru.test.nytn.dto

data class AppState (
    val idle : Boolean = false,
    val emptyList : Boolean = false,
    val loading : Boolean = false,
    val error : Boolean = false
        )