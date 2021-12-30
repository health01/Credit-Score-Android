package com.kst.creditscoreapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.JsonSyntaxException
import com.kst.creditscoreapp.mockData.MockCreditService
import com.kst.creditscoreapp.network.CreditService
import com.kst.creditscoreapp.network.CreditState
import com.kst.creditscoreapp.repository.CreditRepository
import com.kst.creditscoreapp.repository.CreditRepositoryImpl
import com.kst.creditscoreapp.ui.main.credit.CreditFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class CreditViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var service: CreditService

    lateinit var creditRepository: CreditRepository

    private lateinit var viewModel: CreditFragmentViewModel

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun dropdown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `retrieve JsonSyntaxException when Repository return array format`() {
        runBlocking {
            setupModel("endpointArray.json")
            viewModel.getNewData()
            val uiState: CreditState = viewModel.uiState.value
            assertThat(uiState, `is`(IsNull.notNullValue()))
            assertThat(uiState, instanceOf(CreditState.Error::class.java))
            uiState as CreditState.Error
            assertThat(uiState.exception, instanceOf(JsonSyntaxException::class.java))
        }
    }

    @Test
    fun `retrieve localScoreDetail when Repository return correct data set`() {
        runBlocking {
            setupModel()
            viewModel.getNewData()

            val uiState: CreditState = viewModel.uiState.value
            assertThat(uiState, `is`(IsNull.notNullValue()))
            assertThat(uiState, instanceOf(CreditState.Success::class.java))
            uiState as CreditState.Success
            assertThat(uiState.localScoreDetail!!.score, `is`("514"))
            assertThat(uiState.localScoreDetail!!.maxScore, `is`("700"))
            assertThat(viewModel.getAngle(10f), `is`(36))
        }
    }

    @Test
    fun `retrieve NullPointerException when Repository return incorrect data set`() {
        runBlocking {

            setupModel("endpointMissingData.json")
            viewModel.getNewData()
            val uiState: CreditState = viewModel.uiState.value as CreditState.Error

            assertThat(uiState, `is`(IsNull.notNullValue()))
            assertThat(uiState, instanceOf(CreditState.Error::class.java))
            uiState as CreditState.Error
            assertThat(uiState.exception, instanceOf(NullPointerException::class.java))
        }
    }


    private fun setupModel(fileName: String = "endpoint.json") {
        service = MockCreditService(fileName)
        creditRepository = CreditRepositoryImpl(service)
        viewModel = CreditFragmentViewModel(creditRepository, testDispatcher)
    }
}