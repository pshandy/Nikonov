package ru.pshandy.android.cinema

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ru.pshandy.android.cinema.domain.MovieItem

class MovieListFragment : Fragment() {

    interface Callbacks {
        fun onMovieSelected(id: Int)
    }
    private var callbacks: Callbacks? = null

    private var adapter: MovieAdapter? = MovieAdapter(emptyList())
    private val movieListViewModel: MovieListViewModel by lazy {
        ViewModelProviders.of(this).get(MovieListViewModel::class.java)
    }
    private lateinit var movieRecyclerView: RecyclerView

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie_list, container, false)
        movieRecyclerView = view.findViewById(R.id.movie_recycler_view) as RecyclerView
        movieRecyclerView.layoutManager = LinearLayoutManager(context)
        movieRecyclerView.adapter = adapter
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI(movieListViewModel.getMovies())
    }
    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    private fun updateUI(movies: List<MovieItem>) {
        adapter = MovieAdapter(movies)
        movieRecyclerView.adapter = adapter
    }


    // ---------------------------------- RecyclerView ---------------------------------- 
    private inner class MovieItemHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val nameRuTextView: TextView = itemView.findViewById(R.id.movie_item_title)
        val descriptionTextView: TextView = itemView.findViewById(R.id.movie_item_description)
        private val posterImageView: ImageView = itemView.findViewById(R.id.movie_item_poster)

        private lateinit var movieItem: MovieItem

        fun bind(movieItem: MovieItem) {
            this.movieItem = movieItem
            descriptionTextView.text = this.movieItem.description
            nameRuTextView.text = this.movieItem.nameRu
            //TODO posterImageView
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            callbacks?.onMovieSelected(movieItem.id)
        }
        
    }

    private inner class MovieAdapter(var movies: List<MovieItem>) : RecyclerView.Adapter<MovieItemHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemHolder {
            val view = layoutInflater.inflate(R.layout.fragment_movie_list_item, parent, false)
            return MovieItemHolder(view)
        }
        override fun onBindViewHolder(holder: MovieItemHolder, position: Int) {
            val movie = movies[position]
            holder.bind(movie)
        }
        override fun getItemCount() = movies.size
    }

    companion object {

        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }

    }

}