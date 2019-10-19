package com.trinitas.smkcoding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.singpaulee.made_dicoding.bank.Movie
import com.trinitas.smkcoding.connection.ConfigRetrofit
import com.trinitas.smkcoding.connection.MovieInterface
import com.trinitas.smkcoding.model.ResponseMovieModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_movie.view.*
import org.jetbrains.anko.support.v4.toast

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    lateinit var rootView : View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for the
       rootView = inflater.inflate(R.layout.fragment_movie, container, false)



//        val list = Movie.listMovie as ArrayList<MovieModel>
//        val layoutmanager = LinearLayoutManager(activity)
//        val adapter = MovieAdapter(list,activity!!.applicationContext)
//
//        rootView.rv_movie.apply {
//            layoutManager = layoutmanager
//            setAdapter(adapter)
//        }

        return rootView
    }

    private fun getListMovie(){
       val movieNowPlaying =
           ConfigRetrofit.retrofitConfig()
            .create(MovieInterface::class.java)
            .getListMovieNowPlaying(
                "230c1cbc8264d7792851fe922aa6623a")

        movieNowPlaying
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({respons ->
                //menerima response yang berhasil

                val list = respons.results
        val layoutmanager =
            LinearLayoutManager(activity)
        val adapter =
            MovieAdapter(list,activity!!.applicationContext)

                rootView.rv_movie.apply {
                    layoutManager = layoutmanager
                setAdapter(adapter)
                }

            },{
                //menerima response yang gagal
                toast("gagal")
            })
    }
}
