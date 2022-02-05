package com.techyourchance.dagger2course.screens.questionslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.techyourchance.dagger2course.MyApplication
import com.techyourchance.dagger2course.R
import com.techyourchance.dagger2course.questions.FetchQuestionsUseCase
import com.techyourchance.dagger2course.questions.Question
import com.techyourchance.dagger2course.screens.common.ScreensNavigator
import com.techyourchance.dagger2course.screens.common.activities.BaseActivity
import com.techyourchance.dagger2course.screens.common.dialogs.DialogsNavigator
import com.techyourchance.dagger2course.screens.common.dialogs.ServerErrorDialogFragment
import com.techyourchance.dagger2course.screens.common.fragments.BaseFragment
import com.techyourchance.dagger2course.screens.questiondetails.QuestionDetailsActivity
import kotlinx.coroutines.*

class QuestionsListFragment : BaseFragment(), QuestionsListViewMvc.Listener {


    //activity doesnot know anything about implementation deatils or logic, it only tells controller class
    // to perform the actions it doesnot care about the implemtation details.
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)


    private var isDataLoaded = false

    private lateinit var viewMvc: QuestionsListViewMvc

    private lateinit var fetchQuestionsUseCase: FetchQuestionsUseCase

    private lateinit var dialogsNavigator: DialogsNavigator


    private lateinit var screensNavigator: ScreensNavigator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //this class only uses fetchQuestionUseCase it doesnot use stackoverflow Api anyhwhere so this class doesnot need to know about it.

//        fetchQuestionsUseCase = FetchQuestionsUseCase((application as MyApplication).fetchQuestionsUseCase)

        fetchQuestionsUseCase = compositionRoot.fetchQuestionsUseCase

        dialogsNavigator = compositionRoot.dialogsNavigator

        screensNavigator = compositionRoot.screensNavigator

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewMvc = compositionRoot.viewMvcFactory.newQuestionsListViewMvc(container)
        return viewMvc.rootView
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        if (!isDataLoaded) {
            fetchQuestions()
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
        coroutineScope.coroutineContext.cancelChildren()
    }

    private fun fetchQuestions() {
        coroutineScope.launch {
            viewMvc.showProgressIndication()
            try {
                when (val result = fetchQuestionsUseCase.fetchLatestQuestions()) {
                    is FetchQuestionsUseCase.Result.Success -> {
                        viewMvc.bindQuestions(result.questions)
                        isDataLoaded = true
                    }
                    is FetchQuestionsUseCase.Result.Failure -> {
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


    override fun onRefreshClicked() {
        fetchQuestions()
    }

    override fun onQuestionClicked(clickedQuestion: Question) {
        screensNavigator.toQuestionsDetails(clickedQuestion.id)
    }


}