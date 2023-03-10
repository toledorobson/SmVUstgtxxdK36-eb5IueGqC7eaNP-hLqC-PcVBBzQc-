package com.akinguldere.etsturcase.model

import com.google.gson.annotations.SerializedName

/*
{
      "poster_path": "/vC324sdfcS313vh9QXwijLIHPJp.jpg",
      "popularity": 47.432451,
      "id": 31917,
      "backdrop_path": "/rQGBjWNveVeF8f2PGRtS85w9o9r.jpg",
      "vote_average": 5.04,
      "overview": "Based on the Pretty Little Liars series of young adult novels by Sara Shepard, the series follows the lives of four girls — Spencer, Hanna, Aria, and Emily — whose clique falls apart after the disappearance of their queen bee, Alison. One year later, they begin receiving messages from someone using the name \"A\" who threatens to expose their secrets — including long-hidden ones they thought only Alison knew.",
      "first_air_date": "2010-06-08",
      "origin_country": [
        "US"
      ],
      "genre_ids": [
        18,
        9648
      ],
      "original_language": "en",
      "vote_count": 133,
      "name": "Pretty Little Liars",
      "original_name": "Pretty Little Liars"
    }
 */

data class MovieItem(
    val overview: String,
    val id: Int,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("backdrop_path")
    val backdrop: String,
    @SerializedName("vote_average")
    val vote: Double,
    val name: String,
    @SerializedName("first_air_date")
    val date: String,
    @SerializedName("original_language")
    val language: String

) : java.io.Serializable
