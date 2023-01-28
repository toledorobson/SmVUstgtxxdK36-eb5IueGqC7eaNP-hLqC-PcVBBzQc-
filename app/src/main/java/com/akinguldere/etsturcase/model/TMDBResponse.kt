package com.akinguldere.etsturcase.model

import com.google.gson.annotations.SerializedName

data class TMDBResponse(
    val page: Int,
    @SerializedName("results")
    val movies: ArrayList<MovieItem>
)