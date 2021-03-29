package com.example.cinemex.view

import android.os.Bundle
import android.os.Parcelable
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.cinemex.DataManager
import com.example.cinemex.adapters.MovieListAdapter
import com.example.cinemex.R
import com.example.cinemex.contracts.MovieListContract
import com.example.cinemex.domain.model.MovieDomain
import com.example.cinemex.presenter.MovieListPresenter
import com.example.cinemex.utils.ScreenNavigator
import com.example.cinemex.view.common.activities.BaseActivity
import com.facebook.drawee.backends.pipeline.Fresco
import kotlinx.android.synthetic.main.activity_movie_list_activity.*
import javax.inject.Inject

class MovieListActivity : BaseActivity(), MovieListContract.View, MovieListAdapter.MovieListener,TimePickerFragment.onTimeChange{

    @Inject lateinit var presenter: MovieListContract.Presenter
    @Inject lateinit var mScreenNavigator: ScreenNavigator
    private var viewAdapter: MovieListAdapter = MovieListAdapter(this)
    private var listState: Parcelable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        Fresco.initialize(this)
        setContentView(R.layout.activity_movie_list_activity)
        setSupportActionBar(findViewById(R.id.toolbar2))
        presenter.onAttach(this)
        presenter.fetchMovies(DataManager.timeLimit)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        listState = recyclerView.layoutManager?.onSaveInstanceState()
        outState.putParcelable(MY_LIST_STATE_KEY,listState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        listState = savedInstanceState.getParcelable(MY_LIST_STATE_KEY)
    }

    override fun onResume() {
        super.onResume()
        listState?.let {
            recyclerView.layoutManager?.onRestoreInstanceState(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.settings -> {
                mScreenNavigator.goToTimePicker()
                true
            }
            else -> false
        }
    }

    override fun setUpAdapter() {
        if(noMovieMesssage.visibility == View.VISIBLE){
            noMovieMesssage.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
        recyclerView.adapter = viewAdapter
    }

    override fun showNoMovieMessage(time: Int) {
        recyclerView.visibility = View.GONE
        noMovieMesssage.visibility = View.VISIBLE
        noMovieMesssage.text = getString(R.string.no_movies_message,time)
    }

    override fun showMessage(msg: String) {
        Toast.makeText(
                this,
                getString(R.string.error_message,msg),
                Toast.LENGTH_LONG).show()
    }

    override fun onDestroy(){
       super.onDestroy()
       presenter.detachView()
   }

    override fun navigateTo() {
    }

    override fun setList(mList: List<MovieDomain>) {
        viewAdapter.refreshData(mList)
    }

    override fun onShowTrailer(movieId: String, posterUrl: String) {
        mScreenNavigator.onShowTrailer(movieId,posterUrl)
    }

    override fun onFetchMoviesNewTime(time: Int) {
        presenter.fetchMovies(time)
    }

    companion object {
        const val MY_LIST_STATE_KEY = "myListState"
    }

}