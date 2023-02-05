package ru.pshandy.android.cinema

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import ru.pshandy.android.cinema.domain.Movie

private const val ARG_MOVIE_ID = "Movie_id"

class MovieFragment : Fragment() {

    private lateinit var movie: Movie

    private lateinit var movieTitleTextView: TextView
    private lateinit var movieDescriptionTextView: TextView
    private lateinit var movieGenreTextView: TextView
    private lateinit var movieCountryTextView: TextView
    private lateinit var imageView: ImageView
    private lateinit var imageButton: ImageButton
    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProvider(this)[MovieListViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        movie = Movie()
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
        imageView = view.findViewById(R.id.imageView) as ImageView
        imageButton = view.findViewById(R.id.movie_back)
        imageButton.setOnClickListener() {
            parentFragmentManager.popBackStack()
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = arguments?.getSerializable(ARG_MOVIE_ID) as Int
        this.movie = movieListViewModel.getMovie(movieId)
        updateUI()
    }

    private fun updateUI() {
        movieTitleTextView.text = movie.nameRu
        movieDescriptionTextView.text = movie.description
        movieGenreTextView.text = "Жанры: " + movie.genres.joinToString(", ")
        movieCountryTextView.text = "Страны: " + movie.countries.joinToString(", ")
        Picasso.get()
            .load(movie.posterUrl)
            .into(imageView)
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