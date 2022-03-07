package com.techyourchance.dagger2course.common.dependencyinjection.presentation

import com.techyourchance.dagger2course.common.dependencyinjection.activity.ActivityComponent
import com.techyourchance.dagger2course.common.dependencyinjection.app.PresentationScope
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListActivity
import com.techyourchance.dagger2course.screens.questionslist.QuestionsListFragment
import dagger.Component
import dagger.Subcomponent


//it is a wrapper around modules we get access to modules through this interface.
// by this we can use all the components in activity component class in presentation module class.
@PresentationScope
@Subcomponent(modules = [PresentationModule::class])
interface PresentationComponent {


    //this is a dagger convention

    //isse hum vo moduke ke services inject kar skte hai is fragment etc mein.
    fun inject(fragment: QuestionsListFragment)

    fun inject(activity: QuestionsListActivity)

    fun inject(activity: QuestionDetailsActivity)


//    //this is the convention of dagger
//    fun viewMvcFactory(): ViewMvcFactory
//
//    fun screensNavigator(): ScreensNavigator
//
//    fun dialogNavigator(): DialogsNavigator
//
//    fun fetchQuestionUseCase(): FetchQuestionsUseCase
//
//    fun fetchQuestionDetailsUseCase(): FetchQuestionsDetailsUseCase


}