package com.techyourchance.dagger2course.common.composition

import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.viewsmvc.ViewMvcFactory

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {


    private val fragmentManager get() = activity.supportFragmentManager

    private val layoutInflater get() = LayoutInflater.from(activity)

    val viewMvcFactory get() = ViewMvcFactory(layoutInflater)


    //it will tied with the lifecycle of activity composition root
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }


    val dialogsNavigator by lazy {
        DialogsNavigator(fragmentManager)
    }


    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(appCompositionRoot.stackOverFlowApi)
    val fetchQuestionsDetailsUseCase get() = FetchQuestionsDetailsUseCase(appCompositionRoot.stackOverFlowApi)
}