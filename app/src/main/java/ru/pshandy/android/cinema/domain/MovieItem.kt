package ru.pshandy.android.cinema.domain

import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class MovieItemDto(
    var films: List<MovieItem>
)

data class MovieItem(
    val filmId: Int,
    var posterUrl: String,
    var nameRu: String,
    var description: String
)