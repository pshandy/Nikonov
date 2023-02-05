package ru.pshandy.android.cinema.domain

import java.util.*

data class Movie(
    val id: Int = 0,
    var posterUrl: String = "",
    var nameRu: String = "",
    var description: String = "",
    var genres: List<String> = listOf(""),
    var countries: List<String> =listOf("")
) {
}