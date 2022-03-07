package com.techyourchance.dagger2course.common.dependencyinjection.app

import androidx.annotation.UiThread
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.networking.StackoverflowApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


//hint to maintainers everything in this class should be reported on ui thread.

//for injecting global services
@UiThread
@Module
class AppModule {

    //retrofit should be initialised here as it is only instantiated once
    //always use lazy initialisation with global services.

    @Provides
    fun retrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun stackOverFlowApi(retrofit: Retrofit): StackoverflowApi =
        retrofit.create(StackoverflowApi::class.java)


}