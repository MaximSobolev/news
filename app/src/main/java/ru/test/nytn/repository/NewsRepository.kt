package ru.test.nytn.repository

import ru.test.nytn.dto.News

interface NewsRepository {
    suspend fun getNews(year : String, month: String) : List<News>
    fun getNYTLink() : String
}