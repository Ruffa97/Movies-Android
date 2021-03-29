package com.example.cinemex.presenter

import com.example.cinemex.contracts.MovieListContract
import com.example.cinemex.domain.model.MovieDomain
import com.example.cinemex.interactor.UseCaseImp
import com.example.cinemex.presenter.common.BasePresenter
import com.example.cinemex.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieListPresenter @Inject constructor(private val useCase: UseCaseImp) : BasePresenter<MovieListContract.View>(), MovieListContract.Presenter {

    override fun fetchMovies(time: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            when (val response = useCase.fetchMovies(time)) {
                is Resource.Success -> {
                    if (response.info.movieList.isNotEmpty()) {
                        movieList(response.info.movieList)
                    } else {
                        noMovie(time)
                    }
                }
                is Resource.Failure -> onFailure(response.msg!!)
            }
        }
    }

    override fun onFailure(msg: String) {
        view?.run {
            view?.showMessage(msg)
        }
    }

    override fun noMovie(time: Int) {
        view?.showNoMovieMessage(time)
    }

    override fun movieList(movieList: List<MovieDomain>) {
        view?.setList(movieList)
        view?.setUpAdapter()
    }
}