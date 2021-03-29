package com.example.cinemex.presenter

import com.example.cinemex.contracts.SplashContract
import com.example.cinemex.presenter.common.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashPresenter @Inject constructor(): BasePresenter<SplashContract.View>(),SplashContract.Presenter {

    override fun startTimer() {
        val job = CoroutineScope(Dispatchers.Main) .launch {
            delay(2000)
            view?.navigateTo()
        }
    }
}