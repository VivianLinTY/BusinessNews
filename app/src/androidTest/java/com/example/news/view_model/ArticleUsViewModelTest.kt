package com.example.news.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.statement.UiThreadStatement
import androidx.test.platform.app.InstrumentationRegistry
import com.example.news.repository.ArticleRepository
import com.example.news.response.ArticleResponse
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class ArticleUsViewModelTest {

    @MockK
    private lateinit var mockRepository: ArticleRepository

    private lateinit var viewModel: ArticleUsViewModel

    private val mockLiveData = MutableLiveData<ArticleResponse>()

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        every { mockRepository.getDashBoardNews("us") } returns mockLiveData

        val application = InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as Application
        viewModel = ArticleUsViewModel(application, mockRepository)
    }

    @Test
    fun testGetDashboardNews() {
        val mockArticleResponse = ArticleResponse()
        mockLiveData.postValue(mockArticleResponse)

        verify { mockRepository.getDashBoardNews("us") }
    }

    @Test
    fun testGetDashboardNews_Error() {
        coEvery { mockRepository.getDashBoardNews("us") } throws Exception("Network error")
        val latch = CountDownLatch(1)
        UiThreadStatement.runOnUiThread {
            val observer = Observer<ArticleResponse> { response ->
                if (response == null) {
                    latch.countDown()
                }
            }
            viewModel.dashboardNewsResponseLiveData.observeForever(observer)
            viewModel.onCleared()
            coVerify { mockRepository.getDashBoardNews("us") }
            viewModel.dashboardNewsResponseLiveData.removeObserver(observer)
        }
        latch.await(2, TimeUnit.SECONDS)
    }

    @Test
    fun testOnCleared() {
        viewModel.onCleared()
        
        verify { mockRepository.cancelRequests() }
    }
}
