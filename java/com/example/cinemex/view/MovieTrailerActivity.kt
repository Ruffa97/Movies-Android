package com.example.cinemex.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import com.example.cinemex.BuildConfig
import com.example.cinemex.R
import com.facebook.drawee.view.SimpleDraweeView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class MovieTrailerActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {

    private lateinit var backButton: ImageButton
    private lateinit var youTubePlayerView: YouTubePlayerView
    private lateinit var backgroundPoster: SimpleDraweeView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_trailer)

        youTubePlayerView = findViewById(R.id.youTubePlayer)
        backgroundPoster = findViewById(R.id.backgroundPoster)
        backButton = findViewById(R.id.backButton)
        finishActivity()
        youTubePlayerView.initialize(BuildConfig.API_KEY,this)
        backgroundPoster.setImageURI(intent.getStringExtra(BACKGROUND_POSTER))
    }

    private fun finishActivity(){
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.cueVideo(intent.getStringExtra(MOVIE_TRAILER_KEY))
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        finish()
        showToastMessage(p1.toString())
    }
    fun showToastMessage(msg: String){
        val toast = Toast.makeText(applicationContext, msg, Toast.LENGTH_LONG)
        toast.show()
    }

    companion object{
        var MOVIE_TRAILER_KEY = "MovieTrailerId"
        var BACKGROUND_POSTER = "PosterUrl"
        fun start(context: Context,movieTrailerId: String,posterUrl: String){
            val intent: Intent = Intent(context,MovieTrailerActivity::class.java).apply{
                putExtra(MOVIE_TRAILER_KEY,movieTrailerId)
                putExtra(BACKGROUND_POSTER,posterUrl)
            }
            context.startActivity(intent)
        }
    }
}