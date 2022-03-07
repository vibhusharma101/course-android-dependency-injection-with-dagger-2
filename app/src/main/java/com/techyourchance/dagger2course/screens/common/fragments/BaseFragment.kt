package com.techyourchance.dagger2course.screens.common.fragments

import androidx.fragment.app.Fragment
import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationModule
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity

open class BaseFragment : Fragment() {

    //fragment should have their own composition root not of the activtiies their compostion root is scoped to their own lifecycle.
//    private val compositionRoot by lazy {
//        PresentationModule((requireActivity() as BaseActivity).activityCompositionRoot)
//    }

    private val presentationComponent by lazy {

        (requireActivity() as BaseActivity).activityComponent.newPresentationComponent(
            PresentationModule()
        )
//        DaggerPresentationComponent.builder()
//            .activityComponent((requireActivity() as BaseActivity).activityComponent)
//            .presentationModule(
//                PresentationModule()
//            ).build()
    }
    protected val injector get() = presentationComponent

}