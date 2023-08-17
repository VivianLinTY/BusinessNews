package com.example.news.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.news.repository.ArticleRepository
import com.example.news.response.ArticleResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleTwViewModelTest {

    @MockK
    private lateinit var mockRepository: ArticleRepository

    private lateinit var viewModel: ArticleTwViewModel

    private val mockLiveData = MutableLiveData<ArticleResponse>()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { mockRepository.getDashBoardNews("tw") } returns mockLiveData

        val application = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        viewModel = ArticleTwViewModel(application, mockRepository)
    }

    @Test
    fun testGetDashboardNews() {
        val mockArticleResponse = ArticleResponse()
        mockLiveData.postValue(mockArticleResponse)

        verify { mockRepository.getDashBoardNews("tw") }
    }

    @Test
    fun testOnCleared() {
        viewModel.onCleared()

        verify { mockRepository.cancelRequests() }
    }
}