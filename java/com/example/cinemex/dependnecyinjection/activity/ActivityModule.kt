package com.example.cinemex.dependnecyinjection.activity

import androidx.appcompat.app.AppCompatActivity
import com.example.cinemex.contracts.MovieListContract
import com.example.cinemex.contracts.SplashContract
import com.example.cinemex.presenter.MovieListPresenter
import com.example.cinemex.presenter.SplashPresenter
import com.example.cinemex.utils.ScreenNavigator
import com.example.cinemex.view.MovieListActivity
import com.example.cinemex.view.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule() {

    @Binds
    abstract fun provideSplashView(splashActivity: SplashActivity): SplashContract.View

    @Binds
    abstract fun provideMovieListView(movieListActivity: MovieListActivity): MovieListContract.View

    @ActivityScope
    @Binds
    abstract fun providesSplashPresenter(splashPresenter: SplashPresenter): SplashContract.Presenter

    @ActivityScope
    @Binds
    abstract fun providesMovieListPresenter(movieListPresenter: MovieListPresenter): MovieListContract.Presenter

    companion object {
        @Provides
        fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

        @ActivityScope
        @Provides
        fun screensNavigator(activity: AppCompatActivity) = ScreenNavigator(activity, fragmentManager(activity))
    }

}