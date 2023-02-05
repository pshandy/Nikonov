package ru.pshandy.android.cinema

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import ru.pshandy.android.cinema.domain.Movie
import java.util.*

private const val ARG_MOVIE_ID = "Movie_id"

class MovieFragment : Fragment() {

    private lateinit var movie: Movie

    private lateinit var movieTitleTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieGenreTextView: TextView
    private lateinit var movieCountryTextView: TextView
    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }
    //TODO image

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = Movie();
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        movieTitleTextView = view.findViewById(R.id.movie_title) as TextView
        movieDescriptionTextView = view.findViewById(R.id.movie_description) as TextView
        movieGenreTextView = view.findViewById(R.id.movie_genre) as TextView
        movieCountryTextView = view.findViewById(R.id.movie_countries) as TextView
        //TODO image
        return view;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val crimeId = arguments?.getSerializable(ARG_MOVIE_ID) as Int
        this.movie = movieListViewModel.getMovie(crimeId)
        updateUI()
    }

    private fun updateUI() {
        movieTitleTextView.setText(movie.nameRu)
        movieDescriptionTextView.setText(movie.description)
        movieGenreTextView.setText("Жанры: " + movie.genres.joinToString(", "))
        movieCountryTextView.setText("Страны: " + movie.countries.joinToString(", "))
    }

    override fun onStart() {
        super.onStart()
    }

    companion object {
        fun newInstance(movieId: Int): MovieFragment {
            val args = Bundle().apply {
                putSerializable(ARG_MOVIE_ID, movieId)
            }
            return MovieFragment().apply {
                arguments = args
            }
        }
    }

}