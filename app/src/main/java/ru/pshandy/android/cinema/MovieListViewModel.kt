package ru.pshandy.android.cinema

import androidx.lifecycle.ViewModel
import ru.pshandy.android.cinema.domain.Movie
import ru.pshandy.android.cinema.domain.MovieItem
import ru.pshandy.android.cinema.repository.MovieRepository

class MovieListViewModel : ViewModel() {

    private val movieRepository = MovieRepository.get()

    fun getMovies(): List<MovieItem>  = movieRepository.getMovies()
    fun getMovie(id : Int): Movie = movieRepository.getMovie(id)

}