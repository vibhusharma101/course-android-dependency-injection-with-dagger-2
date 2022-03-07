package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.common.dependencyinjection.app.AppComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Component
import dagger.Subcomponent


@Subcomponent(modules = [ActivityModule::class])
interface ActivityComponent {

// by using subcomponents we dont need to explicitly mention that

//    fun activity(): AppCompatActivity
//
//    fun screensNavigator(): ScreensNavigator
//
//    fun stackOverFlowApi(): StackoverflowApi
//
//    fun fragmentManager(): FragmentManager
//
//    fun layoutInflater(): LayoutInflater


    fun newPresentationComponent(presentationModule: PresentationModule): PresentationComponent

}