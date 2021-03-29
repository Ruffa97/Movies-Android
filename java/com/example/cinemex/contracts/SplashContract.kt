package com.example.cinemex.contracts



interface SplashContract {
    interface View: BaseContract.View{

    }
    interface Presenter: BaseContract.Presenter<View>{
        fun startTimer()
    }
}