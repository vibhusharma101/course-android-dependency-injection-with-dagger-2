package com.techyourchance.dagger2course.screens.questiondetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.Constants
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.common.dependencyinjection.Service
import com.techyourchance.dagger2course.networking.StackoverflowApi
import com.techyourchance.dagger2course.questions.FetchQuestionsDetailsUseCase
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.ServerErrorDialogFragment
import com.techyourchance.dagger2course.screens.viewsmvc.ViewMvcFactory
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class QuestionDetailsActivity : BaseActivity(), QuestionDetailsViewMvc.Listener {

    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    private lateinit var questionId: String

    private lateinit var viewMvc: QuestionDetailsViewMvc

    @Inject
    lateinit var fetchQuestionsDetailsUseCase: FetchQuestionsDetailsUseCase

    @Inject
    lateinit var dialogsNavigator: DialogsNavigator

    @Inject
    lateinit var screensNavigator: ScreensNavigator

    @Inject
    lateinit var viewMvcFactory: ViewMvcFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        injector.inject(this)
        viewMvc = viewMvcFactory.newQuestionsDetailsViewMvc(null)

        setContentView(viewMvc.rootView)

        // retrieve question ID passed from outside
        questionId = intent.extras!!.getString(EXTRA_QUESTION_ID)!!
//following law of delimeters
//        fetchQuestionsDetailsUseCase =
//            FetchQuestionsDetailsUseCase((application as MyApplication).stackOverFlowApi)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchQuestionDetails()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestionDetails() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionsDetailsUseCase.fetchQuestionDetails(questionId)) {
                    is FetchQuestionsDetailsUseCase.Result.Success -> {
                        viewMvc.setQuestionsText(result.questionResponse)
                    }
                    is FetchQuestionsDetailsUseCase.Result.Failure -> {
                        onFetchFailed()
                    }
                }
            } finally {
                viewMvc.hideProgressIndication()
            }
        }
    }

    private fun onFetchFailed() {
        dialogsNavigator.showServerErrorDialog()
    }


    companion object {
        const val EXTRA_QUESTION_ID = "EXTRA_QUESTION_ID"
        fun start(context: Context, questionId: String) {
            val intent = Intent(context, QuestionDetailsActivity::class.java)
            intent.putExtra(EXTRA_QUESTION_ID, questionId)
            context.startActivity(intent)
        }
    }

    override fun onNavigationUpClicked() {
        screensNavigator.navigateBack()
    }
}