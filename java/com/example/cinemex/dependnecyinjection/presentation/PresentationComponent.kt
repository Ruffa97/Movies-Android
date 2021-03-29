package com.example.cinemex.dependnecyinjection.presentation

import com.example.cinemex.view.MovieListActivity
import com.example.cinemex.view.MovieTrailerActivity
import com.example.cinemex.view.SplashActivity
import com.example.cinemex.view.TimePickerFragment
import dagger.Subcomponent

@PresentationScope
@Subcomponent()
interface PresentationComponent {
    fun inject(timePickerFragment: TimePickerFragment)
    fun inject(movieTrailerActivity: MovieTrailerActivity)
    fun inject(movieListActivity: MovieListActivity)
    fun inject(splashActivity: SplashActivity)
}