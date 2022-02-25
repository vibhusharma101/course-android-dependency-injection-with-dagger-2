package com.techyourchance.dagger2course.common.dependencyinjection

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.screens.common.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {


    //it will tied with the lifecycle of activity composition root
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }

    val stackOverFlowApi: StackoverflowApi = appCompositionRoot.stackOverFlowApi


    val fragmentManager get() = activity.supportFragmentManager

    val layoutInflater: LayoutInflater get() = LayoutInflater.from(activity)

}