package com.techyourchance.dagger2course.common.dependencyinjection.app

import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityScope
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class])
interface AppComponent {

//    fun retrofit(): Retrofit
//
//    fun stackOverFlowApi(): StackoverflowApi

    fun newActivityComponent(activityModule: ActivityModule): ActivityComponent


}