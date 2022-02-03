package com.techyourchance.dagger2course.common.composition

import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator

class ActivityCompositionRoot(
    private val activity: AppCompatActivity,
    private val appCompositionRoot: AppCompositionRoot
) {


    private val fragmentManager get() = activity.supportFragmentManager

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