package ru.test.nytn.repository

import ru.test.nytn.apiService.NewsApiService
import ru.test.nytn.dto.News
import ru.test.nytn.utills.ApiError
import ru.test.nytn.utills.Constants
import ru.test.nytn.utills.NetworkError
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val apiService : NewsApiService
) : NewsRepository {

    override suspend fun getNews(year: String, month: String): List<News> {
        try {
            val response = apiService.getNewsList(year, month, Constants.API_KEY)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            val body = response.body() ?: throw ApiError(response.code(), response.message())
            var newsList = body.response.docs
            if (newsList.size > 100) {
                newsList = cutList(newsList)
            }
        return newsList
        } catch (e: ApiError) {
            throw e
        } catch (e: IOException) {
            throw NetworkError()
        } catch (e: Exception) {
            throw UnknownError()
        }
    }

    override fun getNYTLink(): String {
        return Constants.NYTLink
    }

    private fun cutList(newsList : ArrayList<News>) : ArrayList<News> {
        val newList = arrayListOf<News>()
        var n = 0
        for (item in newsList) {
            newList.add(item)
            n += 1
            if (n == 100) {
                return newList
            }
        }
        return newList
    }
}