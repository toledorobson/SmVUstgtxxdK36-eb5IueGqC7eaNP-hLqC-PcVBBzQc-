package com.akinguldere.etsturcase.repository

import com.akinguldere.etsturcase.api.MoviesService
import javax.inject.Inject

class MoviesRepository @Inject constructor(private val api: MoviesService) {
    suspend fun getPopularMovies(page: Int) = api.getPopularMovies(page = page)
}