package ru.pshandy.android.cinema.repository

import android.content.Context
import ru.pshandy.android.cinema.domain.Movie
import ru.pshandy.android.cinema.domain.MovieItem

class MovieRepository private constructor() {

    fun getMovies(): List<MovieItem> {
        return listOf(
            //TODO
            MovieItem(435,
                "https://kinopoiskapiunofficial.tech/images/posters/kp/435.jpg",
                "Зеленая миля",
                "драма (1999)"
            ),
            MovieItem(329,
                "https://kinopoiskapiunofficial.tech/images/posters/kp/329.jpg",
                "Список Шиндлера",
                "драма (1993)"
            )
        )
    }

    fun getMovie(id : Int) : Movie {
        //TODO
        return Movie(
            435,
            "https://kinopoiskapiunofficial.tech/images/posters/kp/435.jpg",
            "Зеленая миля",
            "Пол Эджкомб — начальник блока смертников в тюрьме «Холодная гора», каждый из узников которого однажды проходит «зеленую милю» по пути к месту казни. Пол повидал много заключённых и надзирателей за время работы. Однако гигант Джон Коффи, обвинённый в страшном преступлении, стал одним из самых необычных обитателей блока.",
            listOf("драма", "криминал", "фэнтези"),
            listOf("США")
        )
    }


    companion object {
        private var INSTANCE: MovieRepository? = null
        fun get(): MovieRepository {
            if (INSTANCE == null) {
                INSTANCE = MovieRepository()
            }
            return INSTANCE as MovieRepository
        }
    }

}