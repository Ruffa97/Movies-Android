package com.example.cinemex.presenter.common

import com.example.cinemex.contracts.BaseContract

abstract class BasePresenter<V: BaseContract.View>: BaseContract.Presenter<V> {
    var view: V? = null

    override fun onAttach(view: V) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}