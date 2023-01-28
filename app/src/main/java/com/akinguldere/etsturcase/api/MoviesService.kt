package com.akinguldere.etsturcase.api

import com.akinguldere.etsturcase.model.TMDBResponse
import com.akinguldere.etsturcase.utils.Constants
import com.akinguldere.etsturcase.utils.Constants.END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {
    @GET(END_POINT)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY,
        @Query("language") language: String = "en-EN",
        @Query("page") page: Int
    ): Response<TMDBResponse>
}