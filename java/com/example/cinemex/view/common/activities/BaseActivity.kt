package com.example.cinemex.view.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.example.cinemex.CinemexApp

open class BaseActivity: AppCompatActivity() {

    private val appComponent get() = (application as CinemexApp).appComponent

    val activityComponent by lazy {
        appComponent.newActivityComponentBuilder().activity(this).build()
    }

    private val presentationComponent by lazy {
        activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}