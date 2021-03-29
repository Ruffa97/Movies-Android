package com.example.cinemex.contracts

import com.example.cinemex.interfaces.Navigation

interface BaseContract {
    interface View: Navigation {

    }
    interface Presenter<V: View> {
        fun detachView()
        fun onAttach(view: V)
    }
}