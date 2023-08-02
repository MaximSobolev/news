package ru.test.nytn.apiService

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.test.nytn.dto.NYTResponse

interface NewsApiService {
    @GET("{year}/{month}.json")
    suspend fun getNewsList(
        @Path("year") year: String,
        @Path("month") month: String,
        @Query("api-key") apiKey: String
    ): Response<NYTResponse>
}