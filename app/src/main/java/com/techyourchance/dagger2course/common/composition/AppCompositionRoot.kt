package com.techyourchance.dagger2course.common.composition

import androidx.annotation.UiThread
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//hint to maintainers everything in this class should be reported on ui thread.

//for injecting global services
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

    val stackOverFlowApi by lazy { retrofit.create(StackoverflowApi::class.java) }


}