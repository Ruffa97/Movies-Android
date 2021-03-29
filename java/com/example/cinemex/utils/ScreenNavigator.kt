package com.example.cinemex.utils

import android.app.Activity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.cinemex.R
import com.example.cinemex.view.MovieTrailerActivity
import com.example.cinemex.view.TimePickerFragment

class ScreenNavigator(private val activity: Activity,private val fragmentManager: FragmentManager){

    fun onShowTrailer(movieTrailerId: String,posterUrl: String) {
        MovieTrailerActivity.start(activity,movieTrailerId,posterUrl)
    }

    fun goToTimePicker(){
        val fragment: TimePickerFragment = TimePickerFragment().newInstance()
        replaceFragment(fragment)
    }

    private fun replaceFragment(fragment: TimePickerFragment) {
        val transaction = fragmentManager.beginTransaction()
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction
                .setCustomAnimations(R.anim.slide_up, R.anim.slide_down, R.anim.slide_up, R.anim.slide_down)
                .addToBackStack(null)
                .replace(R.id.fragmentContainer,fragment)
                .commit()
    }
}