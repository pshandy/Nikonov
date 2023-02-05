package ru.pshandy.android.cinema.domain

import org.json.JSONObject

data class MovieDto(
    val kinopoiskId: Int,
    var posterUrl: String,
    var nameRu: String,
    var description: String,
    var genres: List<JSONObject>,
    var countries: List<JSONObject>
)

data class Movie(
    val kinopoiskId: Int = 0,
    var posterUrl: String = "",
    var nameRu: String = "",
    var description: String = "",
    var genres: List<String> = listOf(""),
    var countries: List<String> =listOf("")
)