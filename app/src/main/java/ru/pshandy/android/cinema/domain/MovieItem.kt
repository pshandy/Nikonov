package ru.pshandy.android.cinema.domain

data class MovieItem(
    val id: Int,
    var posterUrl: String,
    var nameRu: String,
    var description: String
)