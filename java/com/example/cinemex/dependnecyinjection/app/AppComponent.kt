package com.example.cinemex.dependnecyinjection.app

import com.example.cinemex.dependnecyinjection.activity.ActivityComponent
import dagger.Component

@AppScope
@Component(modules = [AppModule::class])
interface AppComponent {

    fun newActivityComponentBuilder(): ActivityComponent.Builder


}