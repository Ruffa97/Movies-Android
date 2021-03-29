package com.example.cinemex.view.common.fragments

import androidx.fragment.app.Fragment
import com.example.cinemex.view.common.activities.BaseActivity

open class BaseFragment: Fragment() {

    private val presentationComponent by lazy {
        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent()
    }

    protected val injector get() = presentationComponent
}