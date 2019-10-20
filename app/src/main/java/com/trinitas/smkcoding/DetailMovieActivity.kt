package com.trinitas.smkcoding

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.trinitas.smkcoding.database.DatabaseContract
import com.trinitas.smkcoding.database.database
import com.trinitas.smkcoding.model.ResultsItem
import kotlinx.android.synthetic.main.activity_detail_movie.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.toast

class DetailMovieActivity : AppCompatActivity() {

    var isMovieFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        val movie =
            intent.getParcelableExtra<ResultsItem>("movie")

        tv_title_movie.text = movie.title
        tv_rating_movie.text = "Rating : ${movie.popularity}"
        tv_description_movie.text = movie.overview
        Glide.with(this).load("https://image.tmdb.org/t/p/w500" + movie?.posterPath)
            .into(iv_poster_movie)


        //cek dulu movie udah favorite atau belum
        checkMovieFavorite(movie)

        favorite.onClick {
            if (isMovieFavorite) {
                deleteMovieFavorite(movie)
            } else {
                addMovieToFavorite(movie)
            }
        }

    }

    private fun deleteMovieFavorite(movie: ResultsItem?) {
        database.use {
            delete(
                ResultsItem.TABLE_FAVORITE,
                "${ResultsItem.COLUMN_TITLE}={title}",
                "title" to movie?.title.toString()
            )
            toast("Movie dihapus dari favorite")
            isMovieFavorite = false
            favorite.text = "Tambah Favorite"
        }
    }

    private fun addMovieToFavorite(movie: ResultsItem?) {
        database.use {
            insert(
                ResultsItem.TABLE_FAVORITE,
                ResultsItem.COLUMN_TITLE to movie?.title,
                ResultsItem.COLUMN_POSTERPATH to movie?.posterPath,
                ResultsItem.COLUMN_RATING to movie?.popularity,
                ResultsItem.COLUMN_DESCRIPTION to movie?.overview
            )
            toast("Berhasil ditambah ke favorite")

            isMovieFavorite = true
            favorite.text = "Hapus Favorite"
        }
    }

    private fun checkMovieFavorite(movie: ResultsItem?) {
        //TODO Pengecekan fim ini sudah favorite atau belum
        database.use {
            var isFavorite = select(ResultsItem.TABLE_FAVORITE)
                .whereArgs(
                    ResultsItem.COLUMN_TITLE + "= {title}",
                    "title" to movie?.title.toString()
                )
            val dataMovie: DatabaseContract? = isFavorite.parseOpt(classParser<DatabaseContract>())

            Log.d("FAVORITEMOVIE", dataMovie.toString())

            if (dataMovie != null) {
                isMovieFavorite = true
                favorite.text = "Hapus Favorite"
            } else {
                isMovieFavorite = false
                favorite.text = "Tambah Favorite"
            }
        }

    }
}
