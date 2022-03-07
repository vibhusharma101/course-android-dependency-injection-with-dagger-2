package com.techyourchance.dagger2course.common.dependencyinjection

import com.techyourchance.dagger2course.common.dependencyinjection.presentation.PresentationComponent
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.viewsmvc.ViewMvcFactory
import java.lang.Exception
import java.lang.reflect.Field

class Injector(private val component: PresentationComponent) {


//    //using reflection to create classes at runtime and which is done by di frameworks
//    fun inject(client: Any) {
//        for (field in getAllFields(client)) {
//            if (isAnnotatedForInjection(field)) {
//                injectField(client, field)
//            }
//        }
//    }
//
//
//    private fun getAllFields(client: Any): Array<out Field> {
//        val clientClass = client::class.java
//        return clientClass.declaredFields
//    }
//
//    private fun isAnnotatedForInjection(field: Field): Boolean {
//        val fieldAnnotations = field.annotations
//        for (annotation in fieldAnnotations) {
//            if (annotation is Service) {
//                return true
//            }
//        }
//        return false
//    }
//
//    private fun injectField(client: Any, field: Field) {
//        val isAccessibleInitially = field.isAccessible
//        field.isAccessible = true
//        field.set(client, getServiceForClass(field.type))
//        field.isAccessible = isAccessibleInitially
//    }
//
//    private fun getServiceForClass(type: Class<*>): Any {
//        when (type) {
//            DialogsNavigator::class.java -> {
//                return component.dialogNavigator()
//            }
//            ScreensNavigator::class.java -> {
//                return component.screensNavigator()
//            }
//            FetchQuestionsUseCase::class.java -> {
//                return component.fetchQuestionUseCase()
//            }
//            FetchQuestionsDetailsUseCase::class.java -> {
//                return component.fetchQuestionDetailsUseCase()
//            }
//            ViewMvcFactory::class.java -> {
//                return component.viewMvcFactory()
//            }
//            else -> {
//                throw  Exception("unsupported service type: $type")
//            }
//        }
//    }
//

}