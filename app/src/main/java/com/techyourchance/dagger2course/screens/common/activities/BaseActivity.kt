package com.techyourchance.dagger2course.screens.common.activities

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityModule
import com.techyourchance.dagger2course.common.dependencyinjection.activity.DaggerActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.app.AppModule
import com.techyourchance.dagger2course.common.dependencyinjection.app.DaggerAppComponent
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule

open class BaseActivity : AppCompatActivity() {


    private val appComponent by lazy { DaggerAppComponent.builder().appModule(AppModule()).build() }

    val activityComponent: ActivityComponent by lazy {
        DaggerActivityComponent.builder().appComponent(appComponent)
            .activityModule(ActivityModule(this)).build()
    }

    private val presentationComponent by lazy {
        //now we are delegating dagger to construct presentation component.
        activityComponent.newPresentationComponent(PresentationModule())
//        DaggerPresentationComponent.builder().activityComponent(activityComponent)
//            .presentationModule(PresentationModule()).build()
    }

    protected val injector get() = presentationComponent


}