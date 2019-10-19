package com.trinitas.smkcoding.connection

import com.trinitas.smkcoding.model.ResponseMovieModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface MovieInterface {

    @GET("movie/now_playing")
    fun getListMovieNowPlaying(
        @Query("api_key")
        apiKey: String
    ): Observable<ResponseMovieModel>
}