package com.example.cinemex.contracts

import com.example.cinemex.adapters.MovieListAdapter
import com.example.cinemex.domain.model.MovieDomain

interface MovieListContract {
    interface View: BaseContract.View,
        MovieListAdapter.MovieListener{
        fun setUpAdapter()
        fun showNoMovieMessage(time: Int)
        fun showMessage(msg: String)

    }
    interface Presenter: BaseContract.Presenter<View>{
        fun fetchMovies(time: Int)
        fun onFailure(msg: String)
        fun noMovie(time: Int)
        fun movieList(movieList: List<MovieDomain>)
    }
}