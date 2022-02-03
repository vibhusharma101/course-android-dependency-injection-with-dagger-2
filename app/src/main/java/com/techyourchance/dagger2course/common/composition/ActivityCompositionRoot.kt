package com.techyourchance.dagger2course.common.composition

import android.app.Activity
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator

class ActivityCompositionRoot(
    private val activity: Activity,
    private val appCompositionRoot: AppCompositionRoot
) {


    //it will tied with the lifecycle of activity composition root
    val screensNavigator by lazy {
        ScreensNavigator(activity)
    }



    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(appCompositionRoot.stackOverFlowApi)
    val fetchQuestionsDetailsUseCase get() = FetchQuestionsDetailsUseCase(appCompositionRoot.stackOverFlowApi)
}