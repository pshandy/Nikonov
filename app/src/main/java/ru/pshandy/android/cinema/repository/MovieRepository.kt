package ru.pshandy.android.cinema.repository

import android.util.Log
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.pshandy.android.cinema.domain.Movie
import ru.pshandy.android.cinema.domain.MovieDto
import ru.pshandy.android.cinema.domain.MovieItem

const val URL_COUNTRY_API = "https://kinopoiskapiunofficial.tech/"

class MovieRepository private constructor() {

    interface MoviesServiceApi {
        @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
        @GET("/api/v2.2/films/top")
        fun getMovies() : Call<Response<Any>>

        @Headers("X-API-KEY: e30ffed0-76ab-4dd6-b41f-4c9da2b2735b")
        @GET("/api/v2.2/films/{id}")
        fun getMovie(@Path("id") kinopoiskId: Int) : Call<MovieDto>

    }

    fun getMovies(): List<MovieItem> {

//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        val httpClient = OkHttpClient.Builder()
//        httpClient.addInterceptor(logging)

//        val retro = Retrofit.Builder()
//            .baseUrl(URL_COUNTRY_API)
//            .addConverterFactory(GsonConverterFactory.create())
//            .client(httpClient.build())
//            .build()
//
//        val service = retro.create(MoviesServiceApi::class.java)
//
//        val countryRequest = service.getMovies()
//
//        countryRequest.enqueue(object : Callback<Response<Any>> {
//
//            override fun onResponse(
//                call: Call<Response<Any>>,
//                response: Response<Response<Any>>
//            ) {
//                Log.d(
//                    MovieRepository::class.simpleName,
//                    response.toString()
//                )
//                if (response != null) {
//                        Log.d(
//                            MovieRepository::class.simpleName,
//                            response.toString()
//                        )
//                }
//
//            }


//            override fun onFailure(call: Call<Response<Any>>, t: Throwable) {
//                Log.i(MovieRepository::class.simpleName, "on FAILURE!!!!")
//            }
//
//        })

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

        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        val retro = Retrofit.Builder()
            .baseUrl(URL_COUNTRY_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()

        val service = retro.create(MoviesServiceApi::class.java)



        val countryRequest = service.getMovie(id)

        countryRequest.enqueue(object : Callback<MovieDto> {

            override fun onResponse(
                call: Call<MovieDto>,
                response: Response<MovieDto>
            ) {
                Log.d(
                    "http",
                    response.toString()
                )
                if (response != null) {
                    Log.d(
                        "http",
                        response.raw().toString()
                    )
                }

            }

            override fun onFailure(call: Call<MovieDto>, t: Throwable) {
                Log.i("http", "on FAILURE!!!!")
                t.printStackTrace()
            }

        })

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