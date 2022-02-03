package com.techyourchance.dagger2course.common.composition

import androidx.annotation.UiThread
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//hint to maintainers everything in this class should be reported on ui thread.
@UiThread
class AppCompositionRoot {

    //retrofit should be initialised here as it is only instantiated once
    //always use lazy initialisation with global services.

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private val stackOverFlowApi by lazy { retrofit.create(StackoverflowApi::class.java) }

    val fetchQuestionsUseCase get() = FetchQuestionsUseCase(stackOverFlowApi)
    val fetchQuestionsDetailsUseCase get() = FetchQuestionsDetailsUseCase(stackOverFlowApi)

}