package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import android.view.LayoutInflater
import androidx.fragment.app.FragmentManager
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.viewsmvc.ViewMvcFactory
import dagger.Module
import dagger.Provides

@Module
class PresentationModule() {


    @Provides
    fun viewMvcFactory(layoutInflater: LayoutInflater) = ViewMvcFactory(layoutInflater)

    @Provides
    fun dialogsNavigator(fragmentManager: FragmentManager) = DialogsNavigator(fragmentManager)

    @Provides
    fun fetchQuestionsUseCase(stackOverFlowApi: StackoverflowApi) =
        FetchQuestionsUseCase(stackOverFlowApi)

    @Provides
    fun fetchQuestionsDetailsUseCase(stackOverFlowApi: StackoverflowApi) =
        FetchQuestionsDetailsUseCase(stackOverFlowApi)
}