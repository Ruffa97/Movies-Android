package com.example.cinemex.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cinemex.R
import com.example.cinemex.contracts.SplashContract
import com.example.cinemex.presenter.SplashPresenter
import com.example.cinemex.view.common.activities.BaseActivity
import javax.inject.Inject

class SplashActivity: BaseActivity(), SplashContract.View{

    @Inject lateinit var presenter: SplashContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_activity)
        presenter.onAttach(this)
        presenter.startTimer()
    }

    override fun navigateTo() {
        val intent = Intent(this@SplashActivity,MovieListActivity::class.java)
        this.startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}