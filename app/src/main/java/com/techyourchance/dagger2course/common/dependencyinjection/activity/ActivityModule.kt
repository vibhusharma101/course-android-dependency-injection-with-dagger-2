package com.techyourchance.dagger2course.common.dependencyinjection.activity

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import dagger.Module
import dagger.Provides


@Module
class ActivityModule(
    private val activity: AppCompatActivity
) {


    @Provides
    fun activity(): AppCompatActivity = activity

    //it will tied with the lifecycle of activity composition root
    @Provides
    fun screensNavigator(activity: AppCompatActivity) =
        ScreensNavigator(activity)

    @Provides
    fun fragmentManager(activity: AppCompatActivity) = activity.supportFragmentManager

    @Provides
    fun layoutInflater(activity: AppCompatActivity): LayoutInflater = LayoutInflater.from(activity)

}